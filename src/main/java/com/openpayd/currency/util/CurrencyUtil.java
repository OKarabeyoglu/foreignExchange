package com.openpayd.currency.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CurrencyUtil {

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

    public static Integer createTransactionIdxNo(OffsetDateTime date) {
        if(Objects.isNull(date)) return null;
        if(OffsetDateTime.MAX.equals(date)) return 99991231; // 9999-12-31
        if(OffsetDateTime.MIN.equals(date)) return 10101; // 0001-01-01
        return Integer.valueOf(date.format(formatter));
    }

    public static Integer createTransactionIdxNo(LocalDate date) {
        if(Objects.isNull(date)) return null;
        if(LocalDate.MAX.equals(date)) return 99991231; // 9999-12-31
        if(LocalDate.MIN.equals(date)) return 10101; // 0001-01-01
        return Integer.valueOf(date.format(formatter));
    }
}
