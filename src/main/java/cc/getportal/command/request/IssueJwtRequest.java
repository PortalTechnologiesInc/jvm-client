package cc.getportal.command.request;

import cc.getportal.command.PortalRequest;
import cc.getportal.command.notification.UnitNotification;
import cc.getportal.command.response.IssueJwtResponse;

public class IssueJwtRequest extends PortalRequest<IssueJwtResponse, UnitNotification> {

    private final String target_key;
    private final long duration_hours;

    public IssueJwtRequest(String targetKey, long durationHours) {
        target_key = targetKey;
        duration_hours = durationHours;
    }


    @Override
    public String name() {
        return "IssueJwt";
    }

    @Override
    public Class<IssueJwtResponse> responseType() {
        return IssueJwtResponse.class;
    }
}
