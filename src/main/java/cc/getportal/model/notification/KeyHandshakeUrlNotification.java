package cc.getportal.model.notification;

import cc.getportal.model.PortalNotification;

import java.util.List;

public record KeyHandshakeUrlNotification(String main_key,
                                          List<String> preferred_relays) implements PortalNotification {

    public String getMainKey() {
        return main_key;
    }

    public List<String> getPreferredRelays() {
        return preferred_relays;
    }
}
