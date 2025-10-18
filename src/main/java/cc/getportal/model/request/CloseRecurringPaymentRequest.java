package cc.getportal.model.request;

import cc.getportal.model.PortalRequest;
import cc.getportal.model.notification.UnitNotification;
import cc.getportal.model.response.CloseRecurringPaymentResponse;

import java.util.List;

public class CloseRecurringPaymentRequest extends PortalRequest<CloseRecurringPaymentResponse, UnitNotification> {

    private final String main_key;
    private final List<String> subkeys;
    private final String subscription_id;

    public CloseRecurringPaymentRequest(String mainKey, List<String> subkeys, String subscriptionId) {
        main_key = mainKey;
        this.subkeys = subkeys;
        subscription_id = subscriptionId;
    }

    @Override
    public String name() {
        return "CloseRecurringPayment";
    }

    @Override
    public Class<CloseRecurringPaymentResponse> responseType() {
        return CloseRecurringPaymentResponse.class;
    }
}
