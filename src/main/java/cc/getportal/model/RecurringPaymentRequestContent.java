package cc.getportal.model;

import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public final class RecurringPaymentRequestContent {
    private final @Nullable String description;
    private final long amount;
    private final Currency currency;
    private final @Nullable String auth_token;
    private final RecurrenceInfo recurrence;
    private final String expires_at;

    public RecurringPaymentRequestContent(
            @Nullable String description,
            long amount,
            Currency currency,
            @Nullable String auth_token,
            RecurrenceInfo recurrence,
            long expires_at
    ) {
        this.description = description;
        this.amount = amount;
        this.currency = currency;
        this.auth_token = auth_token;
        this.recurrence = recurrence;
        this.expires_at = String.valueOf(expires_at);
    }

    public @Nullable String description() {
        return description;
    }

    public long amount() {
        return amount;
    }

    public Currency currency() {
        return currency;
    }

    public @Nullable String auth_token() {
        return auth_token;
    }

    public RecurrenceInfo recurrence() {
        return recurrence;
    }

    public String expires_at() {
        return expires_at;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (RecurringPaymentRequestContent) obj;
        return Objects.equals(this.description, that.description) &&
                this.amount == that.amount &&
                Objects.equals(this.currency, that.currency) &&
                Objects.equals(this.auth_token, that.auth_token) &&
                Objects.equals(this.recurrence, that.recurrence) &&
                Objects.equals(this.expires_at, that.expires_at);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, amount, currency, auth_token, recurrence, expires_at);
    }

    @Override
    public String toString() {
        return "RecurringPaymentRequestContent[" +
                "description=" + description + ", " +
                "amount=" + amount + ", " +
                "currency=" + currency + ", " +
                "auth_token=" + auth_token + ", " +
                "recurrence=" + recurrence + ", " +
                "expires_at=" + expires_at + ']';
    }

}
