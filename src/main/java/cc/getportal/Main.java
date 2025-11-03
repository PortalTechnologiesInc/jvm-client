package cc.getportal;

import cc.getportal.model.Currency;
import cc.getportal.model.InvoiceRequestContent;
import cc.getportal.model.Profile;
import cc.getportal.model.request.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicReference;

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
            logger.info("NOT: KeyHandshakeUrl mainkey {}, relays {}", notification.getMainKey(), notification.getPreferredRelays());
        }), keyHandshakeUrlResponse -> {
            logger.info("KeyHandshakeUrl '{}'", keyHandshakeUrlResponse.url());
        });

        var key = "fb45f982d24c6ddaaed012e28c2514ba7207a08cd738d52e27a5ef6827667900";
        client.sendCommand(new FetchProfileRequest(key), fetchProfileResponse -> {
            logger.info("Fetched profile: {}", fetchProfileResponse);
        });

        client.sendCommand(new SetProfileRequest(new Profile("myname", "mydisp", null, null)), unitResponse -> {
            logger.info("Set profile");
        });

        client.sendCommand(new CloseRecurringPaymentRequest(key, Collections.emptyList(), "subscriptionId"), closeRecurringPaymentResponse -> {
            logger.info("Closed recurring payment {}", closeRecurringPaymentResponse);
        });

        client.sendCommand(new ListenClosedRecurringPaymentRequest(notification -> {
            logger.info("NOT: {}", notification);
        }), listenClosedRecurringPaymentResponse -> {
            logger.info("Listening closed recurring payments");
        });

        client.sendCommand(new RequestInvoiceRequest(key, Collections.emptyList(), new InvoiceRequestContent(
                "request-id",
                10_000,
                Currency.MILLISATS,
                null,
                String.valueOf(Instant.now().plusSeconds(60 * 5).toEpochMilli()),
                "my-description",
                null
        )), requestInvoiceResponse -> {
            logger.info("Request invoice response {}", requestInvoiceResponse);
        });

        AtomicReference<String> issuedJwt = new AtomicReference<>();
        client.sendCommand(new IssueJwtRequest(key, 2), issueJwtResponse -> {
            logger.info("Issued jwt: {}", issueJwtResponse.token());
            issuedJwt.set(issueJwtResponse.token());
        });

        Thread.sleep(1000L * 2);

        client.sendCommand(new VerifyJwtRequest(key, issuedJwt.get()), verifyJwtResponse -> {
            logger.info("VerifyJwt response: {}", verifyJwtResponse);
        });

        Thread.sleep(1000L * 2);
        String relayToAdd = "ws://not-working.com";
        client.sendCommand(new AddRelayRequest(relayToAdd), addRelayResponse -> {
            logger.info("AddRelay response: {}", addRelayResponse);
        });

        client.sendCommand(new RemoveRelayRequest(relayToAdd), removeRelayResponse -> {
            logger.info("RemoveRelay response: {}", removeRelayResponse);
        });

        String minturl = "https://mint.getportal.cc";

        String staticAuthToken = "test-static-token-for-mint-getportal-cc";
        String unit = "multi";

        client.sendCommand(new MintCashuRequest(minturl, staticAuthToken, unit, 1, "A premium ticket"), mintCashuResponse -> {
            logger.info("MintCashu response: {}", mintCashuResponse);

            client.sendCommand(new BurnCashuRequest(minturl, staticAuthToken, unit, mintCashuResponse.token()), burnCashuResponse -> {
                logger.info("BurnCashu response: {}", burnCashuResponse);
            });

        });

        client.sendCommand(new SendCashuDirectRequest(key, Collections.emptyList(), "cashu-token"), sendCashuDirectResponse -> {
            logger.info("SendCashuDirect response: {}", sendCashuDirectResponse);
        });

    }
}