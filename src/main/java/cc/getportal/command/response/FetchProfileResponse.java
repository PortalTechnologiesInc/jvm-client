package cc.getportal.command.response;

import cc.getportal.command.PortalResponse;
import cc.getportal.model.Profile;
import org.jetbrains.annotations.Nullable;

public record FetchProfileResponse(@Nullable Profile profile) implements PortalResponse {
}
