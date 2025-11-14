package cc.getportal.model;

import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public final class RecurrenceInfo {
    private final @Nullable String until;
    private final String calendar;
    private final @Nullable Integer max_payments;
    private final String first_payment_due;

    public RecurrenceInfo(
            @Nullable Long until,
            String calendar,
            @Nullable Integer max_payments,
            long first_payment_due
    ) {
        this.until = until == null ? null : String.valueOf(until);
        this.calendar = calendar;
        this.max_payments = max_payments;
        this.first_payment_due = String.valueOf(first_payment_due);
    }

    public @Nullable String until() {
        return until;
    }

    public String calendar() {
        return calendar;
    }

    public @Nullable Integer max_payments() {
        return max_payments;
    }

    public String first_payment_due() {
        return first_payment_due;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (RecurrenceInfo) obj;
        return Objects.equals(this.until, that.until) &&
                Objects.equals(this.calendar, that.calendar) &&
                Objects.equals(this.max_payments, that.max_payments) &&
                Objects.equals(this.first_payment_due, that.first_payment_due);
    }

    @Override
    public int hashCode() {
        return Objects.hash(until, calendar, max_payments, first_payment_due);
    }

    @Override
    public String toString() {
        return "RecurrenceInfo[" +
                "until=" + until + ", " +
                "calendar=" + calendar + ", " +
                "max_payments=" + max_payments + ", " +
                "first_payment_due=" + first_payment_due + ']';
    }

}
