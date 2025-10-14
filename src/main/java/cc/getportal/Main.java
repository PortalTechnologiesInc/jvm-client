package cc.getportal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws Exception {
        var client = new PortalSDK("http://localhost:3000/health", "ws://localhost:3000/ws");

        client.connect();

        Thread.sleep(2000);

        client.authenticate("token", message -> {

            logger.info("Authenticated response: {}", message);


            logger.info("Is authenticated?: {}", client.isAuthenticated());
        });
    }
}