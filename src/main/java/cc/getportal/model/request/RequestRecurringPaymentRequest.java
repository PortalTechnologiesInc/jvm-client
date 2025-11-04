package cc.getportal.model.request;

import cc.getportal.model.PortalRequest;
import cc.getportal.model.RecurringPaymentRequestContent;
import cc.getportal.model.notification.UnitNotification;
import cc.getportal.model.response.RequestRecurringPaymentResponse;

import java.util.List;

public class RequestRecurringPaymentRequest extends PortalRequest<RequestRecurringPaymentResponse, UnitNotification> {

    private final String main_key;
    private final List<String> subkeys;
    private final RecurringPaymentRequestContent payment_request;

    public RequestRecurringPaymentRequest(String mainKey, List<String> subkeys, RecurringPaymentRequestContent paymentRequest) {
        main_key = mainKey;
        this.subkeys = subkeys;
        payment_request = paymentRequest;
    }

    @Override
    public String name() {
        return "RequestRecurringPayment";
    }

    @Override
    public Class<RequestRecurringPaymentResponse> responseType() {
        return RequestRecurringPaymentResponse.class;
    }
}
