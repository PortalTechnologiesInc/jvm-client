package cc.getportal;

import cc.getportal.command.request.*;
import cc.getportal.command.response.AuthenticateKeyResponse;
import cc.getportal.model.Currency;
import cc.getportal.model.RecurrenceInfo;
import cc.getportal.model.RecurringPaymentRequestContent;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Path;
import java.time.Instant;
import java.util.Collections;
import java.util.List;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws Exception {
        var client = new PortalSDK("http://localhost:3000/health", "ws://localhost:3000/ws")
                .onClose(() -> logger.error("Closed connection"));

        client.connect("token");

        Thread.sleep(2000);

        client.sendCommand(new KeyHandshakeUrlRequest(notification -> {
            client.sendCommand(new AuthenticateKeyRequest(notification.getMainKey(), Collections.emptyList()), (authenticateKeyResponse, err) -> {
                if(err != null) {
                    logger.error(err);
                    return;
                }
                logger.info("AuthenticateKey response: {}", authenticateKeyResponse);

                if (authenticateKeyResponse.event().status().status() == AuthenticateKeyResponse.AuthResponseStatusType.APPROVED) {
                    connected(client, notification.getMainKey(), notification.getPreferredRelays());
                }
            });
        }), (keyHandshakeUrlResponse, err) -> {
            if(err != null) {
                logger.error("error keyhandshake: {}", err);
                return;
            }
            logger.info("KeyHandshakeUrl '{}'", keyHandshakeUrlResponse.url());
            try {
                createQrCode(keyHandshakeUrlResponse.url());
            } catch (WriterException | IOException e) {
                throw new RuntimeException(e);
            }
        });

        Thread.sleep(1000 * 15);
    }

    private static void connected(PortalSDK client, String mainKey, List<String> preferredRelays) {
        logger.info("KeyHandshake with {}", mainKey);

//        client.sendCommand(new RequestSinglePaymentRequest(mainKey, Collections.emptyList(), new SinglePaymentRequestContent(
//                "My first payment in java",
//                5,
//                Currency.FIAT("EUR"),
//                null,
//                null
//        ), notification -> {
//            logger.info("Single payment notification: {}", notification.status());
//        }), (requestSinglePaymentResponse, err) -> {
//            if(err != null) {
//                logger.info("error single payment: {}", err);
//                return;
//            }
//            logger.info("RequestSinglePayment response: {}", requestSinglePaymentResponse);
//        });

        client.sendCommand(new RequestRecurringPaymentRequest(mainKey, Collections.emptyList(), new RecurringPaymentRequestContent(
                "my first recurring payment",
                1,
                Currency.FIAT("EUR"),
                null,
                new RecurrenceInfo(null, "monthly", null, Instant.now().plusSeconds(60 * 1).getEpochSecond()),
                Instant.now().plusSeconds(60 * 5).getEpochSecond()
        )), (requestRecurringPaymentResponse, err) -> {
            if(err != null) {
                logger.error(err);
                return;
            }
            logger.info("RequestRecurringPayment response: {}", requestRecurringPaymentResponse);

            try {
                client.disconnect();
                logger.info("Disconnected");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });


//        client.sendCommand(new CalculateNextOccurrenceRequest("daily", System.currentTimeMillis() / 1000), (res, err) -> {
//            if(err != null) {
//                logger.error("Error calculating next occurrence: {}", err);
//                return;
//            }
//
//            if(res.next_occurrence() == null) {
//                logger.error("Next occurrence invalid");
//                return;
//            }
//
//            logger.info("Next occurrence: {}", Instant.ofEpochSecond(res.next_occurrence()).toString());
//
//        });
    }

    private static void createQrCode(String data) throws WriterException, IOException {
        int size= 48;
        QRCodeWriter writer = new QRCodeWriter();
        BitMatrix matrix = writer.encode(data, BarcodeFormat.QR_CODE, size, size);
        MatrixToImageWriter.writeToPath(matrix, "PNG", Path.of("./qrcode.png"));
    }
}