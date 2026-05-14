package com.applyjob.myshop.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record OrderItemRequest(

        @NotBlank(message = "Không để trống productId trong order item")
        String productId,

        @NotNull(message = "Không để trống số lượng")
        @Positive(message = "Số lượng sản phẩm phải lớn hơn 0" )
        Integer quantity

) {
}