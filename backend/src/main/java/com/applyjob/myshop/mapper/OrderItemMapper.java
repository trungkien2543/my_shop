package com.applyjob.myshop.mapper;

import com.applyjob.myshop.dto.response.OrderItemResponse;
import com.applyjob.myshop.entity.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {

    @Mapping(target = "productId", source = "product.id")
    @Mapping(target = "productName", source = "product.name")
    @Mapping(
            target = "total",
            expression = "java(orderItem.getPrice().multiply(java.math.BigDecimal.valueOf(orderItem.getQuantity())))"
    )
    OrderItemResponse toResponse(OrderItem orderItem);
}