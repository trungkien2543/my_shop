package com.applyjob.myshop.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record ProductRequest(

        @NotBlank(message = "Ten khong duoc de trong")
        String name,

        @Positive(message = "Gia phai lon hon 0")
        BigDecimal price,

        @PositiveOrZero(message = "So luong khong duoc la so am")
        Integer quantity,

        String description

) {
}