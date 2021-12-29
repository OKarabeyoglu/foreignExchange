package com.openpayd.currency.request;

import com.openpayd.currency.dto.SortDto;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PagedApiRequest {
    public static final PagedApiRequest NO_PAGING = new PagedApiRequest(-1, 0);

    @ApiParam(value = "default 0 (first page), -1 means no paging", example = "0")
    private int page = 0;

    @ApiParam(value = "default 20, ignored when no paging", example = "20")
    private int size = 20;

    @ApiParam("property to sort on, default is service specific")
    private SortDto sort;

    public PagedApiRequest(int page, int size) {
        this(page, size, null);
    }

    public boolean usePaging() {
        return page >= 0 && size > 0;
    }
}