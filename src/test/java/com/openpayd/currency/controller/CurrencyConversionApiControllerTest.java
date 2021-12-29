package com.openpayd.currency.controller;

import com.openpayd.currency.dto.CurrencyConversionDto;
import com.openpayd.currency.dto.CurrencyConversionPageDto;
import com.openpayd.currency.request.CalculateExchangeRateRequest;
import com.openpayd.currency.response.CalculateExchangeRateResponse;
import com.openpayd.currency.response.CurrencyConversionListResponse;
import com.openpayd.currency.response.PageData;
import com.openpayd.currency.service.CurrencyConversionCommandService;
import com.openpayd.currency.service.CurrencyConversionQueryService;
import com.openpayd.currency.util.CurrencyUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CurrencyConversionApiControllerTest {

    @Mock
    private CurrencyConversionQueryService currencyConversionQueryService;
    @Mock
    private CurrencyConversionCommandService currencyConversionCommandService;

    @InjectMocks
    private CurrencyConversionApiController currencyConversionApiController;

    private Long transactionId;
    private LocalDate transactionDate;
    private String toCurrency;
    private String fromCurrency;
    private Double sourceAmount;
    private CurrencyConversionDto currencyConversionDto = new CurrencyConversionDto();
    private CalculateExchangeRateRequest calculateExchangeRateRequest = new CalculateExchangeRateRequest();
    private CurrencyConversionListResponse currencyConversionListResponse = new CurrencyConversionListResponse();
    private CurrencyConversionPageDto currencyConversionPageDto = new CurrencyConversionPageDto();

    @BeforeEach
    void setup() {
        toCurrency = "TRY";
        fromCurrency = "USD";
        sourceAmount = Double.valueOf(1000);
        transactionId = 1L;
        transactionDate = LocalDate.now();
        currencyConversionDto.setId(1L);
        currencyConversionDto.setToCurrency(toCurrency);
        currencyConversionDto.setFromCurrency(fromCurrency);
        currencyConversionDto.setSourceAmount(sourceAmount);
        currencyConversionDto.setTransactionDate(OffsetDateTime.now());
        currencyConversionDto.setTransactionIdxNo(CurrencyUtil.createTransactionIdxNo(currencyConversionDto.getTransactionDate()));
        currencyConversionDto.setConvertedAmount(Double.valueOf(10000));
        calculateExchangeRateRequest.setToCurrency("TRY");
        calculateExchangeRateRequest.setFromCurrency("USD");
        calculateExchangeRateRequest.setSourceAmount(Double.valueOf(1000));
        currencyConversionPageDto.setPage(new PageData(1,1,1));
        currencyConversionPageDto.setCurrencyConversionDtoList(new ArrayList<>());
        currencyConversionListResponse.setCurrencyConversionPageDto(currencyConversionPageDto);
    }

    @Test
    void getCurrencyConversionList_validTransactionIdGiven_shouldReturnPageableCurrencyConversionListResponse() {

        when(currencyConversionQueryService.getCurrencyConversionList(transactionId, transactionDate))
                .thenReturn(currencyConversionPageDto);

        currencyConversionListResponse = currencyConversionApiController
                .getCurrencyConversionList(transactionId, transactionDate);

        verify(this.currencyConversionQueryService).getCurrencyConversionList(transactionId, transactionDate);

        assertThat(currencyConversionListResponse).isNotNull();
        assertThat(currencyConversionListResponse.getCurrencyConversionPageDto()).isEqualTo(currencyConversionPageDto);
        assertThat(currencyConversionListResponse.getCurrencyConversionPageDto().getPage())
                .isEqualTo(currencyConversionPageDto.getPage());
    }

    @Test
    void calculateExchangeRate_validCalculateExchangeRateRequestGiven_shouldReturnCurrencyPairRate() {
        //stubbing
        when(currencyConversionCommandService.convertCurrency(toCurrency, fromCurrency, sourceAmount))
                .thenReturn(currencyConversionDto);
        //interaction
        CalculateExchangeRateResponse response = currencyConversionApiController.calculateExchangeRate(calculateExchangeRateRequest);
        //verification
        verify(this.currencyConversionCommandService).convertCurrency(toCurrency, fromCurrency, sourceAmount);
        //assertion
        assertThat(response).isNotNull();
        assertThat(response.getConvertedAmount()).isEqualTo(currencyConversionDto.getConvertedAmount());
        assertThat(response.getTransactionId()).isEqualTo(currencyConversionDto.getId());
    }

}
