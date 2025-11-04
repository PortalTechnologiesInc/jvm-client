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
import java.util.Random;
import java.util.UUID;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws Exception {
        var client = new PortalSDK("http://localhost:3000/health", "ws://localhost:3000/ws");

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
    }

    private static void connected(PortalSDK client, String mainKey, List<String> preferredRelays) {
        logger.info("KeyHandshake with {}", mainKey);

//        client.sendCommand(new RequestSinglePaymentRequest(mainKey, Collections.emptyList(), new SinglePaymentRequestContent(
//                "My first payment in java",
//                10_000,
//                Currency.MILLISATS,
//                null,
//                null
//        ), notification -> {
//            logger.info("Single payment notification: {}", notification.status());
//        }), requestSinglePaymentResponse -> {
//            logger.info("RequestSinglePayment response: {}", requestSinglePaymentResponse);
//        });

        client.sendCommand(new RequestRecurringPaymentRequest(mainKey, Collections.emptyList(), new RecurringPaymentRequestContent(
                10_000,
                Currency.MILLISATS,
                new RecurrenceInfo(null, "minutely", null, Instant.now().plusSeconds(60 * 1).getEpochSecond() + ""),
                null,
                Instant.now().plusSeconds(60 * 5).getEpochSecond() + "",
                null,
                "my first recurring payment",
                "payment-" + new Random().nextInt(100_000)
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

    }

    private static void createQrCode(String data) throws WriterException, IOException {
        int size= 48;
        QRCodeWriter writer = new QRCodeWriter();
        BitMatrix matrix = writer.encode(data, BarcodeFormat.QR_CODE, size, size);
        MatrixToImageWriter.writeToPath(matrix, "PNG", Path.of("./qrcode.png"));
    }
}