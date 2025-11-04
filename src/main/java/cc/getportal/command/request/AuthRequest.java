package cc.getportal.command.request;

import cc.getportal.command.PortalRequest;
import cc.getportal.command.notification.UnitNotification;
import cc.getportal.command.response.AuthResponse;

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
