package cc.getportal;

import cc.getportal.model.Profile;
import cc.getportal.model.request.*;
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

        client.sendCommand(new FetchProfileRequest("fb45f982d24c6ddaaed012e28c2514ba7207a08cd738d52e27a5ef6827667900"), fetchProfileResponse -> {
            logger.info("Fetched profile: {}", fetchProfileResponse);
        });

        client.sendCommand(new SetProfileRequest(new Profile("myname", "mydisp", null, null)), unitResponse -> {
            logger.info("Set profile");
        });
    }
}