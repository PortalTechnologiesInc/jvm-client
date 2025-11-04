package cc.getportal.command.request;

import cc.getportal.command.PortalRequest;
import cc.getportal.command.notification.UnitNotification;
import cc.getportal.command.response.MintCashuResponse;
import org.jetbrains.annotations.Nullable;

public class MintCashuRequest extends PortalRequest<MintCashuResponse, UnitNotification> {
    private final String mint_url;
    @Nullable
    private final String static_auth_token;
    private final String unit;
    private final long amount;
    @Nullable
    private final String description;

    public MintCashuRequest(String mintUrl, @Nullable String staticAuthToken, String unit, long amount, @Nullable String description) {
        mint_url = mintUrl;
        static_auth_token = staticAuthToken;
        this.unit = unit;
        this.amount = amount;
        this.description = description;
    }

    @Override
    public String name() {
        return "MintCashu";
    }

    @Override
    public Class<MintCashuResponse> responseType() {
        return MintCashuResponse.class;
    }
}
