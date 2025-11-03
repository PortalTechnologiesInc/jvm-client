package cc.getportal.model;

public interface PortalNotification {

    default boolean deleteStream() {
        return false;
    }
}
