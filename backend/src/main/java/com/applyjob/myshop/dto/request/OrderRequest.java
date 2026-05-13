package com.applyjob.myshop.dto.request;

import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record OrderRequest(

        @NotBlank(message = "Không để trống tên khách hàng")
        String customerName,

        @NotBlank(message = "Không để trống số điện thoại khách hàng")
        String customerPhone,

        @NotBlank(message = "Không để trống địa chỉ giao hàng")
        String customerAddress,

        List<OrderItemRequest> items

) {
}