package cc.getportal.model.request;

import cc.getportal.model.PortalRequest;
import cc.getportal.model.notification.UnitNotification;
import cc.getportal.model.response.RemoveRelayResponse;

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
