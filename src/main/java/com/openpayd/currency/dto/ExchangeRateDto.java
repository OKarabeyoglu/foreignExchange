package com.openpayd.currency.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

@Getter
@Setter
@ToString
public class ExchangeRateDto {

    private Map<String, Double> data;
}
