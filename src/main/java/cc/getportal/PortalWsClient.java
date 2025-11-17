package cc.getportal;

import java.net.URI;
import java.nio.ByteBuffer;

import cc.getportal.command.request.AuthRequest;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.handshake.ServerHandshake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class PortalWsClient extends WebSocketClient {

    private static final Logger logger = LoggerFactory.getLogger(PortalWsClient.class);

    private final PortalSDK client;

    public PortalWsClient(URI serverUri, Draft draft, PortalSDK client) {
        super(serverUri, draft);
        this.client = client;
    }

    public PortalWsClient(URI serverURI, PortalSDK client) {
        super(serverURI);
        this.client = client;
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        logger.debug("new connection opened");

        client.internalSendCommand(new AuthRequest(client.authToken), (res, err) -> {
            if(err != null) {
                logger.error("error auth request: {}", err);
                return;
            }
            client.setConnected(true);
            logger.info("Connected: {}", res.message());
        });
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        logger.warn("closed with exit code '{}' additional info: '{}'", code, reason);

        client.setConnected(false);

        // Run function on close
        if(client.onClose != null) {
            client.onClose.run();
        }

    }

    @Override
    public void onMessage(String message) {
        logger.debug("received message: {}", message);
        client.callFun(message);
    }

    @Override
    public void onMessage(ByteBuffer message) {
        logger.debug("received ByteBuffer");
    }

    @Override
    public void onError(Exception ex) {
        logger.error("an error occurred", ex);
    }

}