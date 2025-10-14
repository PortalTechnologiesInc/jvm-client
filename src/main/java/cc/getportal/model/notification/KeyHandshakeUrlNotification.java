package cc.getportal.model.notification;

import cc.getportal.model.PortalNotification;

import java.util.List;

public class KeyHandshakeUrlNotification implements PortalNotification {
    final String main_key;
    final List<String> preferred_relays;

    public KeyHandshakeUrlNotification(String mainKey, List<String> preferredRelays) {
        main_key = mainKey;
        preferred_relays = preferredRelays;
    }

    public String getMainKey() {
        return main_key;
    }

    public List<String> getPreferredRelays() {
        return preferred_relays;
    }
}
