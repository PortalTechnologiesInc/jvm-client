package cc.getportal;

import java.util.HashMap;
import java.util.Map;

public class Response {
    final String id;
    final String type;

    // Tutti i campi non riconosciuti andranno qui
    Map<String, Object> extra = new HashMap<>();

    public Response(String id, String type) {
        this.id = id;
        this.type = type;
    }

    boolean isSuccess() {
        return type.equals("success");
    }
}
