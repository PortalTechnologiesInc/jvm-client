package cc.getportal;

import cc.getportal.model.Currency;
import cc.getportal.model.InvoiceRequestContent;
import cc.getportal.model.Profile;
import cc.getportal.model.request.*;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Path;
import java.time.Instant;
import java.util.Collections;
import java.util.List;
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

        client.sendCommand(new KeyHandshakeUrlRequest(notification -> {           connected(client, notification.getMainKey(), notification.getPreferredRelays());
        }), keyHandshakeUrlResponse -> {
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
        String minturl = "https://mint.getportal.cc";

        String staticAuthToken = "test-static-token-for-mint-getportal-cc";
        String unit = "multi";

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        client.sendCommand(new RequestCashuRequest(minturl, unit, 1, mainKey, Collections.emptyList()), requestCashuResponse -> {
            logger.info("RequestCashu response: {}", requestCashuResponse);
        });

    }

    private static void createQrCode(String data) throws WriterException, IOException {
        int size= 24;
        QRCodeWriter writer = new QRCodeWriter();
        BitMatrix matrix = writer.encode(data, BarcodeFormat.QR_CODE, size, size);
        MatrixToImageWriter.writeToPath(matrix, "PNG", Path.of("./qrcode.png"));
    }
}