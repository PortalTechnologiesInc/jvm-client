package cc.getportal.model;

import org.jetbrains.annotations.Nullable;

public record RecurrenceInfo(
        @Nullable String until,
        String calendar,
        @Nullable Integer max_payments,
        String first_payment_due
) {
}
