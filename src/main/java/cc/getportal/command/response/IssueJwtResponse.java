package cc.getportal.command.response;

import cc.getportal.command.PortalResponse;

public record IssueJwtResponse(String token) implements PortalResponse {
}
