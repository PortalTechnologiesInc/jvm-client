package cc.getportal.model.request;

import cc.getportal.model.PortalRequest;
import cc.getportal.model.notification.UnitNotification;
import cc.getportal.model.response.SendCashuDirectResponse;

import java.util.List;

public class SendCashuDirectRequest extends PortalRequest<SendCashuDirectResponse, UnitNotification> {


    private final String main_key;
    private final List<String> subkeys;
    private final String token;

    public SendCashuDirectRequest(String mainKey, List<String> subkeys, String token) {
        main_key = mainKey;
        this.subkeys = subkeys;
        this.token = token;
    }

    @Override
    public String name() {
        return "SendCashuDirect";
    }

    @Override
    public Class<SendCashuDirectResponse> responseType() {
        return SendCashuDirectResponse.class;
    }
}
