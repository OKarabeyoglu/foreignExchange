package com.openpayd.currency.dto;

import com.openpayd.currency.response.PageData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyConversionPageDto {
    private PageData page;
    private List<CurrencyConversionDto> currencyConversionDtoList;
}
