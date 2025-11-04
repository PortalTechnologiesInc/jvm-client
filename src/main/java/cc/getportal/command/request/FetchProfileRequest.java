package cc.getportal.command.request;

import cc.getportal.command.PortalRequest;
import cc.getportal.command.notification.UnitNotification;
import cc.getportal.command.response.FetchProfileResponse;

public class FetchProfileRequest extends PortalRequest<FetchProfileResponse, UnitNotification> {
    private final String main_key;

    public FetchProfileRequest(String mainKey) {
        main_key = mainKey;
    }

    @Override
    public String name() {
        return "FetchProfile";
    }

    @Override
    public Class<FetchProfileResponse> responseType() {
        return FetchProfileResponse.class;
    }
}
