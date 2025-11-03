package cc.getportal;

import cc.getportal.model.PortalNotification;
import cc.getportal.model.PortalRequest;
import cc.getportal.model.PortalResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

public class PortalSDK {

    private static final Logger logger = LoggerFactory.getLogger(PortalSDK.class);

    private final ConcurrentHashMap<String, RegisteredCommand<?, ?>> commands = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, RegisteredNotification<?>> activeStreams = new ConcurrentHashMap<>();

    private final Gson gson;
    private final String healthEndpoint;
    private final String wsEndpoint;

    private boolean connected = false;
    private PortalWsClient wsClient;

    public PortalSDK(@NotNull String healthEndpoint, @NotNull String wsEndpoint) {
        this.gson = new GsonBuilder()
                .registerTypeAdapter(Response.class, new ResponseDeserializer())
                .create();
        this.healthEndpoint = healthEndpoint;
        this.wsEndpoint = wsEndpoint;
    }

    public void connect() {

        {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(this.healthEndpoint))
                    .build();
            String response = client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                    .thenApply(HttpResponse::body)
                    .join();

            boolean is_ok = response.equals("OK");

            if (!is_ok) {
                throw new PortalSDKException("health check not successful");
            }

            logger.info("health check successful");
        }

        wsClient = new PortalWsClient(URI.create(this.wsEndpoint), this);
        wsClient.connect();
    }

    public <T extends PortalRequest<E, N>, E extends PortalResponse, N extends PortalNotification> void sendCommand(@NotNull T req, @NotNull Consumer<E> fun) {
        if (!connected) {
            throw new PortalSDKException("not connected. Use PortalSDK#connect() before.");
        }

        var id = generateId();
        var p = req.isUnit() ? null : this.gson.toJson(req);
        var command = String.format("""
                {
                "id": "%s",
                "cmd": "%s",
                "params": %s
                }
                
                """, id, req.name(), p);
        logger.info("Sending command {}: {}", req.name(), command);

        RegisteredNotification<N> registeredNotification = null;
        if (req.notificationType() != null) {
            registeredNotification = new RegisteredNotification<>(req.notificationType(), req.notificationFun());
        }
        this.commands.put(id, new RegisteredCommand<>(req.name(), req.responseType(), fun, registeredNotification));
        wsClient.send(command);
    }

    private String generateId() {
        return UUID.randomUUID().toString();
    }

    // Internal methods

    void setConnected(boolean connected) {
        this.connected = connected;
    }

    void callFun(@NotNull String msg) {
        Response message = gson.fromJson(msg, Response.class);
        if (message.isSuccess()) {
            Response.Success success = message.success();
            RegisteredCommand registeredCommand = this.commands.get(success.id);

            PortalResponse portalResponse = (PortalResponse) gson.fromJson(success.jsonElement, registeredCommand.responseType);
            registeredCommand.fun.accept(portalResponse);

            // check if stream_id is present

            String streamId = success.streamId;
            if(streamId != null) {
                logger.debug("stream id present {}", streamId);

                var registeredNotification = registeredCommand.registeredNotification;
                if(registeredNotification == null) {
                    logger.error("Losing stream id {} because no registered notification", streamId);
                } else {
                    this.activeStreams.put(streamId, registeredNotification);
                }
            }
        }

        if(message.isNotification()) {
            Response.Notification notification = message.notification();

            RegisteredNotification registeredNotification = this.activeStreams.get(notification.id);

            PortalNotification portalNotification = (PortalNotification) gson.fromJson(notification.jsonElement, registeredNotification.notificationType);
            registeredNotification.fun.accept(portalNotification);


            // remove from active streams map
            if(portalNotification.deleteStream()) {
                this.activeStreams.remove(notification.id);
            }

        }

        if(message.isError()) {
            Response.Error error = message.error();

            RegisteredCommand registeredCommand = this.commands.get(error.id);

            if(registeredCommand == null) {
                logger.error("Unexpected error: {}", error);
            } else {
                logger.error("Error of command `{}`: {}", registeredCommand.cmd, error.message);
            }
        }
    }

    public record RegisteredCommand<E extends PortalResponse, N extends PortalNotification>(String cmd, Class<E> responseType, Consumer<E> fun,  @Nullable RegisteredNotification<N> registeredNotification) {}

    public record RegisteredNotification<N extends PortalNotification>(Class<N> notificationType, Consumer<N> fun){}
}
