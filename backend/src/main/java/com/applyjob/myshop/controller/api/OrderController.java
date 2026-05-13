package com.applyjob.myshop.controller.api;

import com.applyjob.myshop.common.Endpoint;
import com.applyjob.myshop.dto.request.OrderRequest;
import com.applyjob.myshop.dto.response.ApiResponse;
import com.applyjob.myshop.dto.response.OrderResponse;
import com.applyjob.myshop.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Endpoint.Api.ROOT+Endpoint.Api.ORDERS)
@RequiredArgsConstructor
public class OrderController extends BaseController{

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<ApiResponse<OrderResponse>> createOrder(
            @Valid @RequestBody OrderRequest orderRequest
            ) throws BadRequestException {

        return createSuccessResponse(orderService.createOrder(orderRequest));

    }

}
