package com.openpayd.currency.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@ApiModel(parent = PagedApiRequest.class)
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SearchCurrencyConversionTransactionRequest extends PagedApiRequest {
    @ApiModelProperty(value = "transactionId", dataType = "Long", example = "1")
    private Long transactionId;
    @ApiModelProperty(value = "transactionDate", dataType = "LocalDate", example = "2021-12-26")
    private LocalDate transactionDate;
    @ApiModelProperty(value = "transactionIdxNo", dataType = "Integer", example = "20211226")
    private Integer transactionIdxNo;
}