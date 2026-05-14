package com.applyjob.myshop.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record OrderResponse(

        String id,

        String customerName,

        String customerPhone,

        BigDecimal totalPrice,

        LocalDateTime createdAt

) {
}