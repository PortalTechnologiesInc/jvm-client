package cc.getportal.model;
import cc.getportal.PortalSDKException;
import org.jetbrains.annotations.NotNull;

public class Currency {

    public static final Currency MILLISATS = new Currency("Millisats");

    public static Currency FIAT(@NotNull String currency) {
        if(currency.equals("Millisats")) {
            throw new PortalSDKException("Use cc.getportal.model.Currency.MILLISATS instead");
        }
        return new Currency(currency);
    }

    private final String code;

    private Currency(String code) {
        this.code = code;
    }

    @NotNull
    public String getCode() {
        return code;
    }
}
