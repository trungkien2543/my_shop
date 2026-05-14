package com.applyjob.myshop.dto.response;

import java.math.BigDecimal;

public record ProductResponse(

        String id,

        String name,

        BigDecimal price,

        Integer quantity

) {
}