package com.openpayd.currency.controller;

import com.openpayd.currency.response.ExchangeRateResponse;
import com.openpayd.currency.service.ExchangeRateService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ExchangeRateApiControllerTest {

    @Mock
    private ExchangeRateService exchangeRateService;

    @InjectMocks
    private ExchangeRateApiController exchangeRateApiController;

    private ExchangeRateResponse exchangeRateResponse = new ExchangeRateResponse();
    private String toCurrency;
    private String fromCurrency;

    @BeforeEach
    void setup() {
        toCurrency = "TRY";
        fromCurrency = "USD";
        exchangeRateResponse.setRate(Double.valueOf(10));
    }

    @Test
    void getExchangeRate_validToAndFromCurrencyGiven_shouldReturnExchangeRateResponse() {
        when(exchangeRateService.getCurrencyPairRate(fromCurrency, toCurrency)).thenReturn(Double.valueOf(10));

        ExchangeRateResponse response = exchangeRateApiController.getExchangeRate(toCurrency, fromCurrency);

        verify(this.exchangeRateService).getCurrencyPairRate(fromCurrency, toCurrency);

        assertThat(response).isNotNull();
        assertThat(response.getRate()).isEqualTo(exchangeRateResponse.getRate());
    }



}
