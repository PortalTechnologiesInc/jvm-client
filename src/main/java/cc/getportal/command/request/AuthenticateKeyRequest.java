package cc.getportal.command.request;

import cc.getportal.command.PortalRequest;
import cc.getportal.command.notification.UnitNotification;
import cc.getportal.command.response.AuthenticateKeyResponse;

import java.util.List;

public class AuthenticateKeyRequest extends PortalRequest<AuthenticateKeyResponse, UnitNotification> {

    private final String main_key;
    private final List<String> subkeys;

    public AuthenticateKeyRequest(String mainKey, List<String> subkeys) {
        main_key = mainKey;
        this.subkeys = subkeys;
    }

    @Override
    public String name() {
        return "AuthenticateKey";
    }

    @Override
    public Class<AuthenticateKeyResponse> responseType() {
        return AuthenticateKeyResponse.class;
    }
}
