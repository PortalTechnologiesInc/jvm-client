package cc.getportal.command.request;

import cc.getportal.command.PortalRequest;
import cc.getportal.command.notification.UnitNotification;
import cc.getportal.command.response.VerifyJwtResponse;

public class VerifyJwtRequest extends PortalRequest<VerifyJwtResponse, UnitNotification> {

    private final String pubkey;
    private final String token;

    public VerifyJwtRequest(String pubkey, String token) {
        this.pubkey = pubkey;
        this.token = token;
    }


    @Override
    public String name() {
        return "VerifyJwt";
    }

    @Override
    public Class<VerifyJwtResponse> responseType() {
        return VerifyJwtResponse.class;
    }
}
