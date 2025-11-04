package cc.getportal.command.request;

import cc.getportal.command.PortalRequest;
import cc.getportal.command.notification.UnitNotification;
import cc.getportal.command.response.SendCashuDirectResponse;

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
