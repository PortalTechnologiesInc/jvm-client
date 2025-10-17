package cc.getportal.model.request;

import cc.getportal.model.PortalRequest;
import cc.getportal.model.notification.UnitNotification;
import cc.getportal.model.response.FetchProfileResponse;

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
