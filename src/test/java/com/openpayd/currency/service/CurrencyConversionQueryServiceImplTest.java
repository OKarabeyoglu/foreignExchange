package com.openpayd.currency.service;

import com.openpayd.currency.domain.CurrencyConversion;
import com.openpayd.currency.dto.CurrencyConversionPageDto;
import com.openpayd.currency.repository.CurrencyConversionRepository;
import com.openpayd.currency.request.SearchCurrencyConversionTransactionRequest;
import com.openpayd.currency.service.impl.CurrencyConversionQueryServiceImpl;
import com.openpayd.currency.util.CurrencyUtil;
import com.openpayd.currency.util.PagingUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CurrencyConversionQueryServiceImplTest {

    @Mock
    private CurrencyConversionRepository currencyConversionRepository;
    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private CurrencyConversionQueryServiceImpl currencyConversionQueryService;

    private Long transactionId;
    private LocalDate transactionDate;
    private SearchCurrencyConversionTransactionRequest request;
    private Page<CurrencyConversion> currencyConversionPage;


    @BeforeEach
    void setup() {
        transactionId = 1L;
        transactionDate = null;
        request = SearchCurrencyConversionTransactionRequest.builder()
                .transactionDate(transactionDate)
                .transactionId(transactionId)
                .transactionIdxNo(CurrencyUtil.createTransactionIdxNo(transactionDate)).build();
        List<CurrencyConversion> currencyConversionList = new ArrayList<>();
        currencyConversionList.add(CurrencyConversion.builder().id(1L).toCurrency("TRY").fromCurrency("USD").rate(Double.valueOf(10)).build());
        currencyConversionPage = new PageImpl<>(currencyConversionList);
    }

    @Test
    void getCurrencyConversionList_validFromTransactionIdOrTransactionDateGiven_shouldReturnCurrencyConversionPageDto() {
        when(currencyConversionRepository
                .findCurrencyConversionList(Mockito.any(), Mockito.any())).thenReturn(currencyConversionPage);

        CurrencyConversionPageDto currencyConversionPageDto = currencyConversionQueryService
                .getCurrencyConversionList(transactionId, transactionDate);

        verify(currencyConversionRepository).findCurrencyConversionList(Mockito.any(), Mockito.any());

        assertThat(currencyConversionPageDto).isNotNull();
        assertThat(currencyConversionPageDto.getCurrencyConversionDtoList()).isNotNull();
    }
}
