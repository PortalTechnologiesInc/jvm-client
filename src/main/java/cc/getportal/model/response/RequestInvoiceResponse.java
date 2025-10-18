package cc.getportal.model.response;

import cc.getportal.model.PortalResponse;

public record RequestInvoiceResponse(
    String invoice,
    String paymentHash
) implements PortalResponse {

}
