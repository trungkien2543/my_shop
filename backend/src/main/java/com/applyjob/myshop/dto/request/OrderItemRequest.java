package com.applyjob.myshop.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record OrderItemRequest(

        @NotBlank(message = "Khong de trong productId trong order item")
        String productId,

        @NotBlank(message = "Khong de trong so luong san pham")
        @Positive(message = "So luong san pham phai lon hon 0" )
        Integer quantity

) {
}