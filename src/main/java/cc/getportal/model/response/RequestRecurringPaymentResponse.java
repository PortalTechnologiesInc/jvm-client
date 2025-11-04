package cc.getportal.model.response;

import cc.getportal.model.Currency;
import cc.getportal.model.PortalResponse;
import cc.getportal.model.RecurrenceInfo;
import com.google.gson.annotations.SerializedName;
import org.jetbrains.annotations.Nullable;

public record RequestRecurringPaymentResponse(RecurringPaymentResponseContent status) implements PortalResponse {


    public record RecurringPaymentResponseContent(String request_id, RecurringPaymentStatus status) {}

    public record RecurringPaymentStatus(RequestRecurringPaymentStatusType status,
                                                @Nullable String subscription_id,
                                                @Nullable Long authorized_amount,
                                                @Nullable Currency authorized_currency,
                                                @Nullable RecurrenceInfo authorized_recurrence,
                                                @Nullable String reason
    ) {

    }

    public enum RequestRecurringPaymentStatusType {
        @SerializedName("confirmed")
        CONFIRMED,
        @SerializedName("rejected")
        REJECTED
    }
}
