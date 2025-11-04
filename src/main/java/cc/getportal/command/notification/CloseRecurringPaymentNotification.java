package cc.getportal.command.notification;

import cc.getportal.command.PortalNotification;
import org.jetbrains.annotations.Nullable;

public record CloseRecurringPaymentNotification(@Nullable String reason, String subscription_id, String main_key, String recipient) implements PortalNotification {
}
