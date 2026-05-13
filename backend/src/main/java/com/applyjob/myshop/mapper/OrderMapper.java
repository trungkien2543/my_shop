package com.applyjob.myshop.mapper;

import com.applyjob.myshop.dto.request.OrderRequest;
import com.applyjob.myshop.dto.response.OrderResponse;
import com.applyjob.myshop.entity.Order;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring",
        uses = OrderItemMapper.class
)
public interface OrderMapper {

    OrderResponse toResponse(Order order);

    Order toEntity(OrderRequest orderRequest);
}
