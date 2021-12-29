package com.openpayd.currency.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
public class CurrencyConversionDto {
    private Long id;
    private String fromCurrency;
    private String toCurrency;
    private Double rate;
    private Double sourceAmount;
    private Double convertedAmount;
    private OffsetDateTime transactionDate;
    private Integer transactionIdxNo;
}
