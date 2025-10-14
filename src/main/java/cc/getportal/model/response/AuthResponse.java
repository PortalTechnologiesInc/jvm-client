package cc.getportal.model.response;

import cc.getportal.model.PortalResponse;

public class AuthResponse implements PortalResponse {
    final String message;

    public AuthResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
