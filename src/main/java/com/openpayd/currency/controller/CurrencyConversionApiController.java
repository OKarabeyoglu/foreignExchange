package com.openpayd.currency.controller;

import com.openpayd.currency.constants.ApiEndpoints;
import com.openpayd.currency.constants.ApiGroups;
import com.openpayd.currency.dto.CurrencyConversionDto;
import com.openpayd.currency.request.CalculateExchangeRateRequest;
import com.openpayd.currency.response.CalculateExchangeRateResponse;
import com.openpayd.currency.response.CurrencyConversionListResponse;
import com.openpayd.currency.service.CurrencyConversionCommandService;
import com.openpayd.currency.service.CurrencyConversionQueryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@RestController
@RequestMapping(value = ApiEndpoints.CURRENCY_CONVERSION_API)
@Api(value = ApiGroups.CurrencyConversion.NAME)
@RequiredArgsConstructor
public class CurrencyConversionApiController {

    private final CurrencyConversionQueryService currencyConversionQueryService;
    private final CurrencyConversionCommandService currencyConversionCommandService;

    @GetMapping(value = "search")
    @ApiOperation(value = "Search Currency Conversions", notes = "This method get converted currency list by " +
            "transaction id or transaction date")
    public CurrencyConversionListResponse getCurrencyConversionList (@RequestParam(required = false,
            name = "transactionId") Long transactionId,
            @RequestParam(required = false, name = "transactionDate")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate transactionDate) {
       return new CurrencyConversionListResponse(currencyConversionQueryService.getCurrencyConversionList(transactionId, transactionDate));
    }

    @PostMapping(value = "create")
    @ApiOperation(value = "create currency conversion transaction", notes = "this method create currency " +
            "conversion transaction")
    public CalculateExchangeRateResponse calculateExchangeRate(@RequestBody @Valid @NotNull(
            message = "{calculateExchangeRateRequest.can.not.be.null}") CalculateExchangeRateRequest request) {
        CurrencyConversionDto currencyConversionDto = currencyConversionCommandService
                .convertCurrency(request.getToCurrency(), request.getFromCurrency(), request.getSourceAmount());
        return new CalculateExchangeRateResponse(currencyConversionDto.getConvertedAmount(), currencyConversionDto.getId());
    }
}

