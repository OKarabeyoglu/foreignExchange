package com.openpayd.currency.exception;

import com.openpayd.currency.enums.CurrencyValidationRule;

public class BusinessValidationException extends BaseException {
    private static final long serialVersionUID = -8434398516786320374L;

    private final CurrencyValidationRule rule;

    public BusinessValidationException(CurrencyValidationRule rule) {
        this(rule, rule.getDescription());
    }

    public BusinessValidationException(CurrencyValidationRule rule, String message) {
        super(message);
        this.rule = rule;
    }

}