package com.openpayd.currency.service;

import com.openpayd.currency.dto.CurrencyConversionDto;

public interface CurrencyConversionCommandService {

    CurrencyConversionDto convertCurrency(String to, String from, Double amount);
}
