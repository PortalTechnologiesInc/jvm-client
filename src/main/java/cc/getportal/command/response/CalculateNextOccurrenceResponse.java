package cc.getportal.command.response;

import cc.getportal.command.PortalResponse;
import org.jetbrains.annotations.Nullable;

public record CalculateNextOccurrenceResponse(@Nullable Long next_occurrence) implements PortalResponse {
}
