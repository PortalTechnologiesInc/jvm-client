package cc.getportal.command.request;

import cc.getportal.command.PortalRequest;
import cc.getportal.command.notification.UnitNotification;
import cc.getportal.command.response.RequestCashuResponse;

import java.util.List;

public class RequestCashuRequest extends PortalRequest<RequestCashuResponse, UnitNotification> {
    private final String mint_url;
    private final String unit;
    private final long amount;
    private final String recipient_key;
    private final List<String> subkeys;

    public RequestCashuRequest(String mintUrl, String unit, long amount, String recipientKey, List<String> subkeys) {
        mint_url = mintUrl;
        this.unit = unit;
        this.amount = amount;
        recipient_key = recipientKey;
        this.subkeys = subkeys;
    }


    @Override
    public String name() {
        return "RequestCashu";
    }

    @Override
    public Class<RequestCashuResponse> responseType() {
        return RequestCashuResponse.class;
    }
}
