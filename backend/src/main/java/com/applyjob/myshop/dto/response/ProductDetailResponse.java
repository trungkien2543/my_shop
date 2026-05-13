package com.applyjob.myshop.dto.response;

import java.math.BigDecimal;

public record ProductDetailResponse(

        String id,

        String name,

        BigDecimal price,

        Integer quantity,

        String description

) {
}
