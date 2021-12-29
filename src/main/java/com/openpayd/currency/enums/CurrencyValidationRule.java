package com.openpayd.currency.enums;

import com.openpayd.currency.exception.BusinessValidationRule;
import lombok.Getter;

@Getter
public enum CurrencyValidationRule implements BusinessValidationRule {

    SERVICE_CALL_ERROR("SERVICE_CALL_ERROR", "An error occurred while calling data fixer service");

    private final String code;
    private final String description;

    CurrencyValidationRule(String code, String description) {
        this.code = code;
        this.description = description;
    }
}
