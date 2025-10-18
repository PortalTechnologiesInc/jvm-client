package cc.getportal.model;

import org.jetbrains.annotations.Nullable;

public record InvoiceRequestContent(
    String request_id,
    long amount,
    Currency currency,
    @Nullable ExchangeRate current_exchange_rate,
    String expires_at,
    @Nullable String description,
    @Nullable String refund_invoice
) {}
