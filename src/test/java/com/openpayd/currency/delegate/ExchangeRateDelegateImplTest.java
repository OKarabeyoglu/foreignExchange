package com.openpayd.currency.delegate;

import com.openpayd.currency.dto.ExchangeRateDto;
import com.openpayd.currency.util.ExchangeRateServiceProperties;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ExchangeRateDelegateImplTest {
    @Mock
    private RestTemplate restTemplate;
    @Mock
    private ExchangeRateServiceProperties exchangeRateServiceProperties;
    @InjectMocks
    private ExchangeRateDelegateImpl exchangeRateDelegate;

    private String baseCurrency;
    private String targetCurrency;
    private ExchangeRateDto exchangeRateDto;
    private ResponseEntity<ExchangeRateDto> responseEntity;

    @BeforeEach
    void setup() {
        baseCurrency = "TRY";
        targetCurrency = "USD";
        Map<String, Double> data = new HashMap<>();
        data.put("USD", Double.valueOf(10));
        exchangeRateDto = new ExchangeRateDto();
        exchangeRateDto.setData(data);
        responseEntity = new ResponseEntity(exchangeRateDto, HttpStatus.OK);
    }

    @Test
    void retrieveExchangeRate_validBaseCurrencyAndTargetCurrencyGivenShouldReturnCurrencyPairRate(){
        when(restTemplate.exchange(Mockito.any(String.class), Mockito.any(HttpMethod.class), Mockito.any(HttpEntity.class),
                Mockito.eq(ExchangeRateDto.class))).thenReturn(responseEntity);
        when(exchangeRateServiceProperties.getHeaderName()).thenReturn("user-agent");

       Double rate = exchangeRateDelegate.retrieveExchangeRate(baseCurrency, targetCurrency);

       verify(this.restTemplate).exchange(Mockito.any(String.class), Mockito.any(HttpMethod.class), Mockito.any(HttpEntity.class),
               Mockito.eq(ExchangeRateDto.class));

       assertThat(rate).isNotNull();
       assertThat(rate).isEqualTo(Double.valueOf(10));
    }
}
