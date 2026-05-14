package com.applyjob.myshop.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;

public record OrderRequest(

        @NotBlank(message = "Không để trống tên khách hàng")
        String customerName,


        @Size(min = 9, max = 11, message = "Số điên thoại phải có 9 đến 10 ký tự")
        String customerPhone,

        @NotBlank(message = "Không để trống địa chỉ giao hàng")
        String customerAddress,

        @Valid
        @NotEmpty(message = "Không để trống danh sách đơn hàng")
        List<OrderItemRequest> items

) {
}