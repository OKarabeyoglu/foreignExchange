package com.openpayd.currency.repository;

import com.openpayd.currency.domain.CurrencyConversion;
import com.openpayd.currency.request.SearchCurrencyConversionTransactionRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyConversionRepository extends JpaRepository<CurrencyConversion, Long> {

    @Query("SELECT c " +
           "FROM CurrencyConversion c " +
           "WHERE :#{#request.transactionId} IS NULL OR c.id = :#{#request.transactionId} " +
           "AND :#{#request.transactionIdxNo} IS NULL OR c.transactionIdxNo = :#{#request.transactionIdxNo}")
    Page<CurrencyConversion> findCurrencyConversionList(@Param("request")
                                                              SearchCurrencyConversionTransactionRequest request,
                                                              Pageable pageable);
}

