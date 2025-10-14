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
                LinkedHashMap<String, Object> data  = context.deserialize(obj.get("data"), LinkedHashMap.class);
                String successType = (String) data.get("type");
                yield new Response.Success(id, successType, data);
            }

            case "notification" -> {
                LinkedHashMap<String, Object> data  = context.deserialize(obj.get("data"), LinkedHashMap.class);
                String notificationType = (String) data.get("type");
                yield new Response.Notification(id, notificationType, data);
            }

            default -> throw new IllegalStateException("Unexpected value: " + type);
        };
    }
}
