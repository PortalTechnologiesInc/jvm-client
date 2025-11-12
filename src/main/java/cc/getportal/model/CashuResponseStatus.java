package cc.getportal.model;

import com.google.gson.annotations.SerializedName;
import org.jetbrains.annotations.Nullable;

public record CashuResponseStatus(
    Status status,
    @Nullable String token,
    @Nullable String reason
) {

    public enum Status {
        @SerializedName("success")
        SUCCESS,
        @SerializedName("insufficient_funds")
        INSUFFICIENT_FUNDS,
        @SerializedName("rejected")
        REJECTED
        ;
    }
}
