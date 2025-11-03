package cc.getportal.model.response;

import cc.getportal.model.CashuResponseStatus;
import cc.getportal.model.PortalResponse;

public record RequestCashuResponse(CashuResponseStatus status) implements PortalResponse {
}
