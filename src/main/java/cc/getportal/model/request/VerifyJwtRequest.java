package cc.getportal.model.request;

import cc.getportal.model.PortalRequest;
import cc.getportal.model.notification.UnitNotification;
import cc.getportal.model.response.VerifyJwtResponse;

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
