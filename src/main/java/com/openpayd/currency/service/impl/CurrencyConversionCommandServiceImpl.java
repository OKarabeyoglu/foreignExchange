package com.openpayd.currency.service.impl;

import com.openpayd.currency.domain.CurrencyConversion;
import com.openpayd.currency.dto.CurrencyConversionDto;
import com.openpayd.currency.dto.CurrencyPairDto;
import com.openpayd.currency.repository.CurrencyConversionRepository;
import com.openpayd.currency.service.CurrencyConversionCommandService;
import com.openpayd.currency.service.ExchangeRateService;
import com.openpayd.currency.util.CurrencyUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional
public class CurrencyConversionCommandServiceImpl implements CurrencyConversionCommandService {

    private final CurrencyConversionRepository currencyConversionRepository;
    private final ExchangeRateService exchangeRateService;
    private final ModelMapper modelMapper;

    @Override
    public CurrencyConversionDto convertCurrency(String toCurrency, String fromCurrency, Double sourceAmount) {
        Objects.requireNonNull(toCurrency, "Argument ‘toCurrency’ can not be null!");
        Objects.requireNonNull(fromCurrency, "Argument ‘fromCurrency’ can not be null!");
        Objects.requireNonNull(sourceAmount, "Argument ‘amount’ can not be null!");
        Double rate = exchangeRateService.getCurrencyPairRate(fromCurrency, toCurrency);
        return saveCurrencyConversionTransaction(toCurrency, fromCurrency, sourceAmount, rate);
    }

    private CurrencyConversionDto saveCurrencyConversionTransaction(String to, String from, Double amount,
                                                                    Double rate) {
        CurrencyConversion currencyConversion = CurrencyConversion.builder()
                .fromCurrency(from)
                .toCurrency(to)
                .rate(rate)
                .sourceAmount(amount)
                .convertedAmount(rate * amount)
                .transactionDate(OffsetDateTime.now())
                .transactionIdxNo(CurrencyUtil.createTransactionIdxNo(OffsetDateTime.now()))
                .build();
        return  modelMapper.map(currencyConversionRepository.save(currencyConversion),
                CurrencyConversionDto.class);
    }
}
