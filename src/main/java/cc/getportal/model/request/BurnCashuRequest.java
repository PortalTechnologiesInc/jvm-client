package cc.getportal.model.request;

import cc.getportal.model.PortalRequest;
import cc.getportal.model.notification.UnitNotification;
import cc.getportal.model.response.BurnCashuResponse;
import cc.getportal.model.response.MintCashuResponse;
import org.jetbrains.annotations.Nullable;

public class BurnCashuRequest extends PortalRequest<BurnCashuResponse, UnitNotification> {
    private final String mint_url;
    @Nullable
    private final String static_auth_token;
    private final String unit;
    private final String token;

    public BurnCashuRequest(String mintUrl, @Nullable String staticAuthToken, String unit, String token) {
        mint_url = mintUrl;
        static_auth_token = staticAuthToken;
        this.unit = unit;
        this.token = token;
    }

    @Override
    public String name() {
        return "BurnCashu";
    }

    @Override
    public Class<BurnCashuResponse> responseType() {
        return BurnCashuResponse.class;
    }
}
