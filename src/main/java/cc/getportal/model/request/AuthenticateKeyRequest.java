package cc.getportal.model.request;

import cc.getportal.model.PortalRequest;
import cc.getportal.model.notification.UnitNotification;
import cc.getportal.model.response.AuthenticateKeyResponse;

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
        return null;
    }
}
