package com.openpayd.currency.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

public final class ApiGroups {
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class CurrencyConversion {
        public static final String NAME = "currency-conversion-api";
        public static final String PATHS = "/api/currency-conversion/**";
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class ExchangeRate {
        public static final String NAME = "exchange-rate-api";
        public static final String PATHS = "/api/exchange-rate/**";
    }
}
