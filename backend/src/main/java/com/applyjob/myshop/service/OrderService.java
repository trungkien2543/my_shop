package com.applyjob.myshop.service;

import com.applyjob.myshop.dto.request.OrderRequest;
import com.applyjob.myshop.dto.response.OrderResponse;
import org.apache.coyote.BadRequestException;

public interface OrderService {

    OrderResponse createOrder(OrderRequest orderRequest) throws BadRequestException;
}
