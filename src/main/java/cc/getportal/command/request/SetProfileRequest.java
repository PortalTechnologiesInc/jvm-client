package cc.getportal.command.request;

import cc.getportal.command.PortalRequest;
import cc.getportal.model.Profile;
import cc.getportal.command.notification.UnitNotification;
import cc.getportal.command.response.UnitResponse;

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
