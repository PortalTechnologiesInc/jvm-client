package cc.getportal.command.response;

import cc.getportal.command.PortalResponse;

public record RequestInvoiceResponse(
    String invoice,
    String paymentHash
) implements PortalResponse {

}
