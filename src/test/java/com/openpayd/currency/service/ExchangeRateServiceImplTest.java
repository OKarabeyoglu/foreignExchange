package com.openpayd.currency.service;

import com.openpayd.currency.delegate.ExchangeRateDelegate;
import com.openpayd.currency.service.impl.ExchangeRateServiceImpl;
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
public class ExchangeRateServiceImplTest {

    @Mock
    private ExchangeRateDelegate exchangeRateDelegate;

    @InjectMocks
    private ExchangeRateServiceImpl exchangeRateService;

    private String toCurrency;
    private String fromCurrency;

    @BeforeEach
    void setup() {
        toCurrency = "TRY";
        fromCurrency = "USD";
    }

    @Test
    void getCurrencyPairRate_validFromCurrencyAndToCurrencyGiven_shouldReturnCurrencyPairRate() {
        when(exchangeRateDelegate.retrieveExchangeRate(fromCurrency, toCurrency)).thenReturn(Double.valueOf(10));

        Double rate = exchangeRateService.getCurrencyPairRate(fromCurrency, toCurrency);

        verify(this.exchangeRateDelegate).retrieveExchangeRate(fromCurrency, toCurrency);

        assertThat(rate).isNotNull();
        assertThat(rate).isEqualTo(Double.valueOf(10));
    }
}
