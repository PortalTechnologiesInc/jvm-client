package cc.getportal.command;

public interface PortalNotification {

    default boolean deleteStream() {
        return false;
    }
}
