package cc.getportal;

import cc.getportal.model.Currency;
import com.google.gson.*;
import java.lang.reflect.Type;

class CurrencyDeserializer implements JsonDeserializer<Currency> {
    @Override
    public Currency deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        String code = json.getAsString();
        if ("Millisats".equals(code)) {
            return Currency.MILLISATS;
        } else {
            return Currency.FIAT(code);
        }
    }
}
