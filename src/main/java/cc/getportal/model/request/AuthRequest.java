package cc.getportal.model.request;

import cc.getportal.model.PortalRequest;
import cc.getportal.model.notification.UnitNotification;
import cc.getportal.model.response.AuthResponse;

public class AuthRequest extends PortalRequest<AuthResponse, UnitNotification> {
    final String token;

    public AuthRequest(String token) {
        this.token = token;
    }

    @Override
    public String name() {
        return "Auth";
    }

    @Override
    public Class<AuthResponse> responseType() {
        return AuthResponse.class;
    }
}
