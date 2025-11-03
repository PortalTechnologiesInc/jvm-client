package cc.getportal.model.response;

import cc.getportal.model.PortalResponse;
import com.google.gson.annotations.SerializedName;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public record AuthenticateKeyResponse(AuthResponseData event) implements PortalResponse {

    public record AuthResponseData(String user_key, String recipient, String challenge, AuthResponseStatus status) {
    }

    public record AuthResponseStatus(AuthResponseStatusType status, @Nullable String reason, @Nullable List<String> granted_permissions,
                                     @Nullable String session_token) {
    }

    public enum AuthResponseStatusType {
        @SerializedName("approved")
        APPROVED,
        @SerializedName("declined")
        DECLINED
    }
}
