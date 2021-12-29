package com.openpayd.currency.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;

@Entity
@Table(indexes = {@Index(name = "IX_TRANSACTION_IDX_NO", columnList = "transactionIdxNo")})
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyConversion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    private String fromCurrency;
    @NotNull
    private String toCurrency;
    @NotNull
    @Column(scale = 6)
    private Double rate;
    @NotNull
    private Double sourceAmount;
    @NotNull
    private Double convertedAmount;
    @NotNull
    private OffsetDateTime transactionDate;
    @NotNull
    private Integer transactionIdxNo;
}