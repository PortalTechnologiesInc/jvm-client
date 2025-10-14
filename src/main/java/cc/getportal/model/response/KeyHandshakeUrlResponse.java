package cc.getportal.model.response;

import cc.getportal.model.PortalResponse;

public class KeyHandshakeUrlResponse implements PortalResponse {

    final String url;

    public KeyHandshakeUrlResponse(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
