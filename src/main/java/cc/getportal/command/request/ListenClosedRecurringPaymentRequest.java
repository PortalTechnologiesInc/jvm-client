package cc.getportal.command.request;

import cc.getportal.command.PortalRequest;
import cc.getportal.command.notification.CloseRecurringPaymentNotification;
import cc.getportal.command.response.UnitResponse;
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
