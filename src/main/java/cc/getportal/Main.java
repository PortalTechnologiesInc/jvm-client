package cc.getportal;

import cc.getportal.model.request.AuthRequest;
import cc.getportal.model.request.AuthenticateKeyRequest;
import cc.getportal.model.request.KeyHandshakeUrlRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws Exception {
        var client = new PortalSDK("http://localhost:3000/health", "ws://localhost:3000/ws");

        client.connect();

        Thread.sleep(2000);

        client.sendCommand(new AuthRequest("token"), authResponse -> {
            logger.info("Auth response '{}'", authResponse.message());
        });

        Thread.sleep(2000);

        client.sendCommand(new KeyHandshakeUrlRequest(notification -> {
            logger.info("KeyHandshakeUrl notification mainkey {}, relays {}", notification.getMainKey(), notification.getPreferredRelays());
        }), keyHandshakeUrlResponse -> {
            logger.info("KeyHandshakeUrl '{}'", keyHandshakeUrlResponse.url());
        });
    }
}