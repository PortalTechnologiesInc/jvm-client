package cc.getportal.model.request;

import cc.getportal.model.PortalRequest;
import cc.getportal.model.notification.CloseRecurringPaymentNotification;
import cc.getportal.model.response.UnitResponse;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class ListenClosedRecurringPaymentRequest extends PortalRequest<UnitResponse, CloseRecurringPaymentNotification> {

    public ListenClosedRecurringPaymentRequest(Consumer<CloseRecurringPaymentNotification> notificationFun) {
        super(notificationFun);
    }

    @Override
    public @Nullable Class<CloseRecurringPaymentNotification> notificationType() {
        return CloseRecurringPaymentNotification.class;
    }

    @Override
    public String name() {
        return "ListenClosedRecurringPayment";
    }

    @Override
    public Class<UnitResponse> responseType() {
        return UnitResponse.class;
    }

    @Override
    public boolean isUnit() {
        return true;
    }
}
