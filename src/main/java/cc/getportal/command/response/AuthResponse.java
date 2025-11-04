package cc.getportal.command.response;

import cc.getportal.command.PortalResponse;
import org.jetbrains.annotations.ApiStatus;

@ApiStatus.Internal
public record AuthResponse(String message) implements PortalResponse {
}
