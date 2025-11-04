package cc.getportal.command.request;

import cc.getportal.command.PortalRequest;
import cc.getportal.command.notification.UnitNotification;
import cc.getportal.command.response.RemoveRelayResponse;

public class RemoveRelayRequest extends PortalRequest<RemoveRelayResponse, UnitNotification> {

    private final String relay;

    public RemoveRelayRequest(String relay) {
        this.relay = relay;
    }


    @Override
    public String name() {
        return "RemoveRelay";
    }

    @Override
    public Class<RemoveRelayResponse> responseType() {
        return RemoveRelayResponse.class;
    }
}
