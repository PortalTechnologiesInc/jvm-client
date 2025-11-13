package cc.getportal.command.request;

import cc.getportal.command.PortalRequest;
import cc.getportal.command.notification.UnitNotification;
import cc.getportal.command.response.CalculateNextOccurrenceResponse;

public class CalculateNextOccurrenceRequest extends PortalRequest<CalculateNextOccurrenceResponse, UnitNotification> {

    private final String calendar;
    private final String from;

    public CalculateNextOccurrenceRequest(String calendar, long from) {
        this.calendar = calendar;
        this.from = String.valueOf(from);
    }

    @Override
    public String name() {
        return "CalculateNextOccurrence";
    }

    @Override
    public Class<CalculateNextOccurrenceResponse> responseType() {
        return CalculateNextOccurrenceResponse.class;
    }
}
