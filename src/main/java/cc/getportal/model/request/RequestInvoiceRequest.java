package cc.getportal.model.request;

import cc.getportal.model.InvoiceRequestContent;
import cc.getportal.model.PortalRequest;
import cc.getportal.model.notification.UnitNotification;
import cc.getportal.model.response.RequestInvoiceResponse;

import java.util.List;

public class RequestInvoiceRequest extends PortalRequest<RequestInvoiceResponse, UnitNotification> {

    private final String recipient_key;
    private final List<String> subkeys;
    private final InvoiceRequestContent content;

    public RequestInvoiceRequest(String recipientKey, List<String> subkeys, InvoiceRequestContent content) {
        recipient_key = recipientKey;
        this.subkeys = subkeys;
        this.content = content;
    }

    @Override
    public String name() {
        return "RequestInvoice";
    }

    @Override
    public Class<RequestInvoiceResponse> responseType() {
        return RequestInvoiceResponse.class;
    }
}
