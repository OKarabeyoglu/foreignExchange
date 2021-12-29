package com.openpayd.currency.service;

import com.openpayd.currency.dto.CurrencyConversionPageDto;

import java.time.LocalDate;

public interface CurrencyConversionQueryService {

    CurrencyConversionPageDto getCurrencyConversionList(Long transactionId, LocalDate transactionDate);

}
