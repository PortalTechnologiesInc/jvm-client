package cc.getportal.model.response;

import cc.getportal.model.PortalResponse;

public record IssueJwtResponse(String token) implements PortalResponse {
}
