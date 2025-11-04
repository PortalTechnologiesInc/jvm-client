package cc.getportal.command.request;

import cc.getportal.command.PortalRequest;
import cc.getportal.command.notification.KeyHandshakeUrlNotification;
import cc.getportal.command.response.KeyHandshakeUrlResponse;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class KeyHandshakeUrlRequest extends PortalRequest<KeyHandshakeUrlResponse, KeyHandshakeUrlNotification> {

    private final String static_token;
    private final Boolean no_request;

    public KeyHandshakeUrlRequest(String staticToken, Boolean noRequest, Consumer<KeyHandshakeUrlNotification> notificationFun) {
        super(notificationFun);
        static_token = staticToken;
        no_request = noRequest;
    }

    public KeyHandshakeUrlRequest(Consumer<KeyHandshakeUrlNotification> notificationFun) {
        this(null, null, notificationFun);
    }

    @Override
    public @Nullable Class<KeyHandshakeUrlNotification> notificationType() {
        return KeyHandshakeUrlNotification.class;
    }

    @Override
    public String name() {
        return "NewKeyHandshakeUrl";
    }

    @Override
    public Class<KeyHandshakeUrlResponse> responseType() {
        return KeyHandshakeUrlResponse.class;
    }
}
