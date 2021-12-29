package com.openpayd.currency.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyPairDto {
    private String toCurrency;
    private Double rate;
    private Double convertedAmount;
}
