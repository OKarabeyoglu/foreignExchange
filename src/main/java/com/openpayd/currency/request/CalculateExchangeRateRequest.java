package com.openpayd.currency.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ApiModel
public class CalculateExchangeRateRequest {
    @ApiModelProperty(value = "target currency", dataType = "String", example = "USD")
    @NotBlank
    private String toCurrency;
    @ApiModelProperty(value = "source currency", dataType = "String", example = "TRY")
    @NotBlank
    private String fromCurrency;
    @ApiModelProperty(value = "source amount", dataType = "Double", example = "1000")
    @NotNull
    private Double sourceAmount;
}