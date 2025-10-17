package cc.getportal.model.response;

import cc.getportal.model.PortalResponse;
import cc.getportal.model.Profile;
import org.jetbrains.annotations.Nullable;

public record FetchProfileResponse(@Nullable Profile profile) implements PortalResponse {
}
