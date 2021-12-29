package com.openpayd.currency.response;

import com.openpayd.currency.dto.CurrencyConversionPageDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CurrencyConversionListResponse {
   private CurrencyConversionPageDto currencyConversionPageDto;
}
