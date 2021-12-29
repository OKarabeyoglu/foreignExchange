package com.openpayd.currency.service;

public interface ExchangeRateService {

    Double getCurrencyPairRate(String fromCurrency, String toCurrency);
}
