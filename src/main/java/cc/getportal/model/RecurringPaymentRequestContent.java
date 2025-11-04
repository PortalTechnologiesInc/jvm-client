package cc.getportal.model;

import org.jetbrains.annotations.Nullable;

public record RecurringPaymentRequestContent(
        long amount,
        Currency currency,
        RecurrenceInfo recurrence,
        @Nullable Object current_exchange_rate,
        String expires_at,
        @Nullable String auth_token,
        @Nullable String description,
        String request_id
) {
}
