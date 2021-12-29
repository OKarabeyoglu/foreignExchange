package com.openpayd.currency.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CalculateExchangeRateResponse {
    private Double convertedAmount;
    private Long transactionId;
}
