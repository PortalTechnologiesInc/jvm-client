package cc.getportal.command.request;

import cc.getportal.command.PortalRequest;
import cc.getportal.command.notification.UnitNotification;
import cc.getportal.command.response.AddRelayResponse;

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
