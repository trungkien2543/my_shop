package com.applyjob.myshop.dto.request;

import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record OrderRequest(

        @NotBlank
        String customerName,

        @NotBlank
        String customerPhone,

        @NotBlank
        String customerAddress,

        List<OrderItemRequest> items

) {
}