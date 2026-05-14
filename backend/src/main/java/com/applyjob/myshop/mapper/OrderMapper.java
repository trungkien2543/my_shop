package com.applyjob.myshop.mapper;

import com.applyjob.myshop.dto.request.OrderRequest;
import com.applyjob.myshop.dto.response.OrderDetailResponse;
import com.applyjob.myshop.dto.response.OrderResponse;
import com.applyjob.myshop.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        componentModel = "spring",
        uses = OrderItemMapper.class
)
public interface OrderMapper {

    OrderDetailResponse toDetailResponse(Order order);

    OrderResponse toResponse(Order order);

    @Mapping(target = "items", expression = "java(new java.util.ArrayList<>())")
    Order toEntity(OrderRequest orderRequest);
}
