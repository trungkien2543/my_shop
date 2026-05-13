package com.applyjob.myshop.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record OrderResponse(

        String id,

        String customerName,

        String customerPhone,

        String customerAddress,

        BigDecimal totalPrice,

        LocalDateTime createdAt,

        List<OrderItemResponse> items

) {
}