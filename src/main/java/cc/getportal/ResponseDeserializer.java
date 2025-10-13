package cc.getportal;

import com.google.gson.*;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

class ResponseDeserializer implements JsonDeserializer<Response> {

    @Override
    public Response deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {

        JsonObject obj = json.getAsJsonObject();

        // read "base" fields
        String id = obj.has("id") ? obj.get("id").getAsString() : null;
        String type = obj.has("type") ? obj.get("type").getAsString() : null;

        Response r = new Response(id, type);

        // extra fields
        for (Map.Entry<String, JsonElement> e : obj.entrySet()) {
            String key = e.getKey();
            if (!key.equals("id") && !key.equals("type")) {
                r.extra.put(key, context.deserialize(e.getValue(), Object.class));
            }
        }

        return r;
    }
}
