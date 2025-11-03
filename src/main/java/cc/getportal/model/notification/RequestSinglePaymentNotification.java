package cc.getportal.model.notification;

import cc.getportal.model.PortalNotification;
import com.google.gson.annotations.SerializedName;
import org.jetbrains.annotations.Nullable;

public record RequestSinglePaymentNotification(InvoiceStatus status) implements PortalNotification {

    @Override
    public boolean deleteStream() {
        InvoiceStatusType type = status.status();
        return type == InvoiceStatusType.USER_FAILED || type == InvoiceStatusType.USER_REJECTED;
    }

    public record InvoiceStatus(InvoiceStatusType status, @Nullable String preimage, @Nullable String reason) {
    }

    public enum InvoiceStatusType {
        @SerializedName("paid")
        PAID,
        @SerializedName("timeout")
        TIMEOUT,
        @SerializedName("error")
        ERROR,
        @SerializedName("user_approved")
        USER_APPROVED,
        @SerializedName("user_success")
        USER_SUCCESS,
        @SerializedName("user_failed")
        USER_FAILED,
        @SerializedName("user_rejected")
        USER_REJECTED
    }
}
