package cc.getportal.command.response;

import cc.getportal.command.PortalResponse;

public record VerifyJwtResponse(String target_key) implements PortalResponse {
}
