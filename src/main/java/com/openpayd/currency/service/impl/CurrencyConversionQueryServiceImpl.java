package com.openpayd.currency.service.impl;

import com.openpayd.currency.domain.CurrencyConversion;
import com.openpayd.currency.dto.CurrencyConversionDto;
import com.openpayd.currency.dto.CurrencyConversionPageDto;
import com.openpayd.currency.repository.CurrencyConversionRepository;
import com.openpayd.currency.request.SearchCurrencyConversionTransactionRequest;
import com.openpayd.currency.response.PageData;
import com.openpayd.currency.service.CurrencyConversionQueryService;
import com.openpayd.currency.util.CurrencyUtil;
import com.openpayd.currency.util.PagingUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CurrencyConversionQueryServiceImpl implements CurrencyConversionQueryService {

    private final CurrencyConversionRepository currencyConversionRepository;
    private final ModelMapper modelMapper;

    private Page<CurrencyConversion> searchCurrencyConversion(Long transactionId, LocalDate transactionDate) {
        SearchCurrencyConversionTransactionRequest request = SearchCurrencyConversionTransactionRequest.builder()
                .transactionDate(transactionDate)
                .transactionId(transactionId)
                .transactionIdxNo(CurrencyUtil.createTransactionIdxNo(transactionDate)).build();
        return currencyConversionRepository
                .findCurrencyConversionList(request, PagingUtil.pageable(request));
    }

    @Override
    public CurrencyConversionPageDto getCurrencyConversionList(Long transactionId, LocalDate transactionDate) {
        Page<CurrencyConversion> currencyConversionList = searchCurrencyConversion(transactionId, transactionDate);
        return CurrencyConversionPageDto.builder().page(new PageData(currencyConversionList.getNumber(),
                        currencyConversionList.getTotalPages(), Integer.parseInt(Long.toString(currencyConversionList.getTotalElements()))))
                .currencyConversionDtoList(currencyConversionList.stream().map(c -> modelMapper.map(c, CurrencyConversionDto.class))
                        .collect(Collectors.toList())).build();
    }
}