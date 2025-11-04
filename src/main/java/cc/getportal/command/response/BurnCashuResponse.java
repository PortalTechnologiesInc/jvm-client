package cc.getportal.command.response;

import cc.getportal.command.PortalResponse;

public record BurnCashuResponse(long amount) implements PortalResponse  {
}
