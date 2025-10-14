package cc.getportal;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.jetbrains.annotations.NotNull;
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

    private final Gson gson;
    private final String healthEndpoint;
    private final String wsEndpoint;

    private boolean connected = false;
    private PortalWsClient wsClient;
    private final ConcurrentHashMap<String, Consumer<Response>> commands = new ConcurrentHashMap<>();
    private boolean authenticated = false;

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

    public void authenticate(@NotNull String token, @NotNull Consumer<Response> fun) {

        record Auth(String token) {}
        sendCommand("Auth", new Auth(token), response -> {

            if (response.isSuccess()) {
                this.authenticated = true;
            }
            fun.accept(response);
        });

    }

    private <T> void sendCommand(@NotNull String cmd, @NotNull T params, @NotNull Consumer<Response> fun) {

        if (!connected) {
            throw new PortalSDKException("not connected. Use PortalSDK#connect() before.");
        }

        var id = generateId();
        var p = this.gson.toJson(params);
        var command = String.format("""
                {
                "id": "%s",
                "cmd": "%s",
                "params": %s
                }
                
                """, id, cmd, p);
        logger.info("test {}", command);

        this.commands.put(id, fun);
        wsClient.send(command);
    }

    private String generateId() {
        return UUID.randomUUID().toString();
    }

    public boolean isAuthenticated() {
        return authenticated;
    }


    // Internal methods

    void setConnected(boolean connected) {
        this.connected = connected;
    }

    void callFun(@NotNull String msg) {
        Response message = gson.fromJson(msg, Response.class);
        var fun = this.commands.get(message.id);
        fun.accept(message);
    }

}
