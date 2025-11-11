package cc.getportal;

import cc.getportal.model.Currency;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

class CurrencySerializer implements JsonSerializer<Currency> {
    @Override
    public JsonElement serialize(Currency src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src.getCode());
    }
}
