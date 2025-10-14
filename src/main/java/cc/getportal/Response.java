package cc.getportal;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedHashMap;

public sealed class Response {
    final String id;
    final ResponseType type;

    public enum ResponseType {
        ERROR,
        SUCCESS,
        NOTIFICATION
    }

    public Response(String id, ResponseType type) {
        this.id = id;
        this.type = type;
    }

    public boolean isError() {
        return type == ResponseType.ERROR;
    }

    public boolean isSuccess() {
        return type == ResponseType.SUCCESS;
    }

    public boolean isNotification() {
        return type == ResponseType.NOTIFICATION;
    }


    public Error error() {
        return (Error) this;
    }

    public Success success() {
        return (Success) this;
    }

    public Notification notification() {
        return (Notification) this;
    }

    public static final class Error extends Response {
        final String message;

        public Error(String id, String message) {
            super(id, ResponseType.ERROR);
            this.message = message;
        }
    }

    public static final class Success extends Response {
        final String successType;
        final LinkedHashMap<String, Object> data;
        final JsonElement jsonElement;

        // Optional stream_id field
        final String streamId;
        public Success(String id, String successType, LinkedHashMap<String, Object> data, JsonElement jsonElement, @Nullable String streamId) {
            super(id, ResponseType.SUCCESS);
            this.successType = successType;
            this.data = data;
            this.jsonElement = jsonElement;
            this.streamId = streamId;
        }
    }

    public static final class Notification extends Response {
        final String notificationType;
        final LinkedHashMap<String, Object> data;
        final JsonElement jsonElement;

        public Notification(String id, String notificationType, LinkedHashMap<String, Object> data, JsonElement jsonElement) {
            super(id, ResponseType.NOTIFICATION);
            this.notificationType = notificationType;
            this.data = data;
            this.jsonElement = jsonElement;
        }
    }
}
