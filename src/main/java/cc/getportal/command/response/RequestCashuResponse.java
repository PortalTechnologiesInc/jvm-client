package cc.getportal.command.response;

import cc.getportal.model.CashuResponseStatus;
import cc.getportal.command.PortalResponse;

public record RequestCashuResponse(CashuResponseStatus status) implements PortalResponse {
}
