package com.openpayd.currency.delegate;

import com.openpayd.currency.dto.ExchangeRateDto;
import com.openpayd.currency.enums.CurrencyValidationRule;
import com.openpayd.currency.exception.BusinessValidationException;
import com.openpayd.currency.util.ExchangeRateServiceProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Component
@RequiredArgsConstructor
@Slf4j
public class ExchangeRateDelegateImpl implements ExchangeRateDelegate {
    private final RestTemplate restTemplate;
    private final ExchangeRateServiceProperties exchangeRateServiceProperties;

    @Override
    public Double retrieveExchangeRate(String baseCurrency, String targetCurrency) {
        String retrieveExchangeRateUrl = exchangeRateServiceProperties.getBaseUrl() +
                exchangeRateServiceProperties.getAccessKey() + "&base_currency=" + baseCurrency;
        ResponseEntity<ExchangeRateDto> exchangeRateResult;
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            headers.add(exchangeRateServiceProperties.getHeaderName(), exchangeRateServiceProperties.getHeaderValue());
            HttpEntity<String> entity = new HttpEntity<String>(exchangeRateServiceProperties.getHeaderBody(), headers);
            log.info("exchange service call {}{}", retrieveExchangeRateUrl, entity);
            exchangeRateResult = restTemplate.exchange(retrieveExchangeRateUrl, HttpMethod.GET, entity,
                    ExchangeRateDto.class);
        } catch (Exception ex) {
            log.error("An error occurred while calling exchange rate service", ex);
            throw new BusinessValidationException(CurrencyValidationRule.SERVICE_CALL_ERROR);
        }
        return exchangeRateResult.getBody().getData().get(targetCurrency);
    }

}
