package com.openpayd.currency.controller;

import com.openpayd.currency.constants.ApiEndpoints;
import com.openpayd.currency.constants.ApiGroups;
import com.openpayd.currency.response.ExchangeRateResponse;
import com.openpayd.currency.service.ExchangeRateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping(value = ApiEndpoints.EXCHANGE_RATE_API)
@Api(value = ApiGroups.ExchangeRate.NAME)
@RequiredArgsConstructor
public class ExchangeRateApiController {

    private final ExchangeRateService exchangeRateService;

    @GetMapping(value = "/rate/{toCurrency}/{fromCurrency}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "get currency pair", notes = "This method get exchange rate of fromCurrency")
    public ExchangeRateResponse getExchangeRate(
            @NotBlank(message = "{toCurrency.can.not.be.null}") @PathVariable("toCurrency") String toCurrency,
            @NotBlank(message = "{fromCurrency.can.not.be.null}") @PathVariable("fromCurrency") String fromCurrency) {
        return new ExchangeRateResponse(exchangeRateService.getCurrencyPairRate(fromCurrency, toCurrency));
    }
}
