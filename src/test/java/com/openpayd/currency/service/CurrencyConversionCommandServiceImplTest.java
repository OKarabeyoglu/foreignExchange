package com.openpayd.currency.service;

import com.openpayd.currency.domain.CurrencyConversion;
import com.openpayd.currency.dto.CurrencyConversionDto;
import com.openpayd.currency.repository.CurrencyConversionRepository;
import com.openpayd.currency.service.impl.CurrencyConversionCommandServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.modelmapper.ModelMapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class CurrencyConversionCommandServiceImplTest {

    @Mock
    private CurrencyConversionRepository currencyConversionRepository;
    @Mock
    private ExchangeRateService exchangeRateService;
    @Mock
    private ModelMapper modelMapper;
    @InjectMocks
    private CurrencyConversionCommandServiceImpl currencyConversionCommandService;

    private String toCurrency;
    private String fromCurrency;
    private Double sourceAmount;
    private CurrencyConversion currencyConversion;
    private CurrencyConversionDto currencyConversionDto = new CurrencyConversionDto();

    @BeforeEach
    void setup() {
        toCurrency = "TRY";
        fromCurrency = "USD";
        sourceAmount = Double.valueOf(1000);
        currencyConversion = CurrencyConversion.builder()
                .id(1L)
                .toCurrency(toCurrency)
                .fromCurrency(fromCurrency)
                .sourceAmount(sourceAmount)
                .rate(Double.valueOf(10)).build();
        currencyConversionDto.setId(1L);
        currencyConversionDto.setToCurrency(toCurrency);
        currencyConversionDto.setFromCurrency(fromCurrency);
        currencyConversionDto.setSourceAmount(sourceAmount);
        currencyConversionDto.setRate(Double.valueOf(10));
    }

    @Test
    void convertCurrency_invalidFromCurrencyAndToCurrencyAndSourceAmountGiven_shouldReturnNullCurrencyConversionDto() {
        when(exchangeRateService.getCurrencyPairRate(fromCurrency, toCurrency)).thenReturn(Double.valueOf(10));
        when(currencyConversionRepository.save(Mockito.any())).thenReturn(currencyConversion);
        when(modelMapper.map(Mockito.eq(currencyConversion), Mockito.eq(CurrencyConversionDto.class))).thenReturn(currencyConversionDto);

        CurrencyConversionDto currencyConversionDto = currencyConversionCommandService
               .convertCurrency(toCurrency, fromCurrency, sourceAmount);

        verify(exchangeRateService).getCurrencyPairRate(fromCurrency, toCurrency);
        verify(currencyConversionRepository).save(Mockito.any());
        verify(modelMapper).map(Mockito.eq(currencyConversion), Mockito.eq(CurrencyConversionDto.class));

        assertThat(currencyConversionDto).isNotNull();
        assertThat(currencyConversionDto.getToCurrency()).isEqualTo(toCurrency);
        assertThat(currencyConversionDto.getFromCurrency()).isEqualTo(fromCurrency);
        assertThat(currencyConversionDto.getSourceAmount()).isEqualTo(sourceAmount);
    }
}
