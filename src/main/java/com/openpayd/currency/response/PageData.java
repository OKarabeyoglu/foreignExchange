package com.openpayd.currency.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@AllArgsConstructor
@Getter
public final class PageData implements Serializable {
    private static final long serialVersionUID = 1L;

    private final int pageNumber;
    private final int totalPages;
    private final int totalRecords;
}
