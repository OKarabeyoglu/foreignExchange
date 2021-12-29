package com.openpayd.currency.delegate;

public interface ExchangeRateDelegate {

   Double retrieveExchangeRate(String baseCurrency, String targetCurrency);
}
