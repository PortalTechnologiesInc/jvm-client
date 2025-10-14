package cc.getportal;

import com.google.gson.*;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

class ResponseDeserializer implements JsonDeserializer<Response> {

    @Override
    public Response deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {

        JsonObject obj = json.getAsJsonObject();

        // read "base" fields
        String id = obj.has("id") ? obj.get("id").getAsString() : null;
        String type = obj.has("type") ? obj.get("type").getAsString() : null;

        return switch (type) {

            case "error" -> new Response.Error(id, obj.get("message").getAsString());
            case "success" -> {
                JsonElement jsonElement = obj.get("data");
                LinkedHashMap<String, Object> data  = context.deserialize(jsonElement, LinkedHashMap.class);
                String successType = (String) data.get("type");
                String streamId = (String) data.get("stream_id");
                yield new Response.Success(id, successType, data, jsonElement, streamId);
            }

            case "notification" -> {
                JsonElement jsonElement = obj.get("data");
                LinkedHashMap<String, Object> data  = context.deserialize(jsonElement, LinkedHashMap.class);
                String notificationType = (String) data.get("type");
                yield new Response.Notification(id, notificationType, data, jsonElement);
            }

            default -> throw new IllegalStateException("Unexpected value: " + type);
        };
    }
}
