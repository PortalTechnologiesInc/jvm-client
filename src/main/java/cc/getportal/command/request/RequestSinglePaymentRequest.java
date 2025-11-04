package cc.getportal.command.request;

import cc.getportal.command.PortalRequest;
import cc.getportal.model.SinglePaymentRequestContent;
import cc.getportal.command.notification.RequestSinglePaymentNotification;
import cc.getportal.command.response.RequestSinglePaymentResponse;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Consumer;

public class RequestSinglePaymentRequest extends PortalRequest<RequestSinglePaymentResponse, RequestSinglePaymentNotification> {

    private final String main_key;
    private final List<String> subkeys;
    private final SinglePaymentRequestContent payment_request;


    public RequestSinglePaymentRequest(String mainKey, List<String> subkeys, SinglePaymentRequestContent paymentRequest, Consumer<RequestSinglePaymentNotification> notificationFun) {
        super(notificationFun);
        main_key = mainKey;
        this.subkeys = subkeys;
        payment_request = paymentRequest;
    }

    @Override
    public @Nullable Class<RequestSinglePaymentNotification> notificationType() {
        return RequestSinglePaymentNotification.class;
    }

    @Override
    public String name() {
        return "RequestSinglePayment";
    }

    @Override
    public Class<RequestSinglePaymentResponse> responseType() {
        return RequestSinglePaymentResponse.class;
    }
}
