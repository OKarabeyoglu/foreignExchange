package com.openpayd.currency.service.impl;

import com.openpayd.currency.delegate.ExchangeRateDelegate;
import com.openpayd.currency.service.ExchangeRateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@Transactional
@RequiredArgsConstructor
public class ExchangeRateServiceImpl implements ExchangeRateService {

    private final ExchangeRateDelegate exchangeRateDelegate;

    @Override
    public Double getCurrencyPairRate(String fromCurrency, String toCurrency) {
        Objects.requireNonNull(fromCurrency, "Argument ‘fromCurrency’ can not be null!");
        Objects.requireNonNull(toCurrency, "Argument ‘toCurrency’ can not be null!");
        return exchangeRateDelegate.retrieveExchangeRate(fromCurrency, toCurrency);
    }

}
