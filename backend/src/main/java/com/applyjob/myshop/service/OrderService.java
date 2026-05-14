package com.applyjob.myshop.service;

import com.applyjob.myshop.dto.request.OrderRequest;
import com.applyjob.myshop.dto.response.OrderDetailResponse;
import com.applyjob.myshop.dto.response.OrderResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface OrderService {

    OrderResponse createOrder(OrderRequest orderRequest);

    Page<OrderResponse> getOrders(
            Pageable pageable
    );

    OrderDetailResponse getOrderDetail(
            String id
    );
}
