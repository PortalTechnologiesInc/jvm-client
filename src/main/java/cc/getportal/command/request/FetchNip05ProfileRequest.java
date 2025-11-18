package cc.getportal.command.request;

import cc.getportal.command.PortalRequest;
import cc.getportal.command.notification.UnitNotification;
import cc.getportal.command.response.FetchNip05ProfileResponse;

public class FetchNip05ProfileRequest extends PortalRequest<FetchNip05ProfileResponse, UnitNotification> {

    private final String nip05;

    public FetchNip05ProfileRequest(String nip05) {
        this.nip05 = nip05;
    }

    @Override
    public String name() {
        return "FetchNip05Profile";
    }

    @Override
    public Class<FetchNip05ProfileResponse> responseType() {
        return FetchNip05ProfileResponse.class;
    }
}
