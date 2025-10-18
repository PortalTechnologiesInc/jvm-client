package cc.getportal.model.response;

import cc.getportal.model.PortalResponse;

public record VerifyJwtResponse(String target_key) implements PortalResponse {
}
