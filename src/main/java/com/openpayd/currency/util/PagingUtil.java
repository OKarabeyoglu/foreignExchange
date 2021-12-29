package com.openpayd.currency.util;

import com.openpayd.currency.request.PagedApiRequest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PagingUtil {

    public static Pageable pageable(PagedApiRequest request) {
        return request.usePaging() ? PageRequest.of(request.getPage(), request.getSize(), sort(request)) : null;
    }

    private static Sort sort(PagedApiRequest request) {
        return request.getSort() != null
                ? Sort.by(Sort.Direction.valueOf(request.getSort().getDirection().name()), request.getSort().getField())
                : Sort.by(Sort.Direction.DESC, "id");
    }
}