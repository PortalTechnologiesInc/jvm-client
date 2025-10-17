package cc.getportal.model.request;

import cc.getportal.model.PortalRequest;
import cc.getportal.model.Profile;
import cc.getportal.model.notification.UnitNotification;
import cc.getportal.model.response.UnitResponse;

public class SetProfileRequest extends PortalRequest<UnitResponse, UnitNotification> {

    private final Profile profile;

    public SetProfileRequest(Profile profile) {
        this.profile = profile;
    }

    @Override
    public String name() {
        return "SetProfile";
    }

    @Override
    public Class<UnitResponse> responseType() {
        return UnitResponse.class;
    }
}
