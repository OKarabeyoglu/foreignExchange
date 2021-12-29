package com.openpayd.currency.dto;

import com.openpayd.currency.enums.SortDirection;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SortDto {
    @ApiParam("field on the domain model to sort on")
    private String field;

    @ApiParam("direction of sort operation, default ASC")
    private SortDirection direction = SortDirection.ASC;
}

