package cc.getportal.command.response;

import cc.getportal.command.PortalResponse;
import cc.getportal.model.Nip05Profile;

public record FetchNip05ProfileResponse(Nip05Profile profile) implements PortalResponse {
}
