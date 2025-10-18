package cc.getportal.model;


public record ExchangeRate(
    double rate,
    String source,
    long time
) {}
