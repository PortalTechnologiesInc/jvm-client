package cc.getportal.model.request;

import cc.getportal.model.PortalRequest;
import cc.getportal.model.notification.UnitNotification;
import cc.getportal.model.response.AddRelayResponse;

public class AddRelayRequest extends PortalRequest<AddRelayResponse, UnitNotification> {

    private final String relay;

    public AddRelayRequest(String relay) {
        this.relay = relay;
    }


    @Override
    public String name() {
        return "AddRelay";
    }

    @Override
    public Class<AddRelayResponse> responseType() {
        return AddRelayResponse.class;
    }
}
