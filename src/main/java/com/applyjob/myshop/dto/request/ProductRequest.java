package com.applyjob.myshop.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record ProductRequest(

        @NotBlank(message = "Tên không được để trống")
        String name,

        @Positive(message = "Giá phải lớn hơn 0")
        BigDecimal price,

        @PositiveOrZero(message = "Số lượng sản phẩm không được âm")
        Integer quantity,

        String description

) {
}