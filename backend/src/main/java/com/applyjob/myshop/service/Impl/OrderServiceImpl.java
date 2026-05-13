package com.applyjob.myshop.service.Impl;

import com.applyjob.myshop.dto.request.OrderItemRequest;
import com.applyjob.myshop.dto.request.OrderRequest;
import com.applyjob.myshop.dto.response.OrderResponse;
import com.applyjob.myshop.entity.Order;
import com.applyjob.myshop.entity.OrderItem;
import com.applyjob.myshop.entity.Product;
import com.applyjob.myshop.exception.ResourceNotFoundException;
import com.applyjob.myshop.mapper.OrderMapper;
import com.applyjob.myshop.repository.OrderRepository;
import com.applyjob.myshop.repository.ProductRepository;
import com.applyjob.myshop.service.OrderService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final ProductRepository productRepository;

    private final OrderMapper orderMapper;


    @Override
    @Transactional
    public OrderResponse createOrder(
            OrderRequest request
    ) throws BadRequestException {

        Order order = orderMapper.toEntity(request);

        BigDecimal totalPrice = BigDecimal.ZERO;

        List<OrderItemRequest> sortedItems = request.items()
                .stream()
                .sorted(
                        Comparator.comparing(
                                OrderItemRequest::productId
                        )
                )
                .toList();

        for (OrderItemRequest itemRequest : sortedItems) {

            Product product = productRepository
                    .findByIdForUpdate(
                            itemRequest.productId()
                    )
                    .orElseThrow(() ->
                            new ResourceNotFoundException(
                                    "Product not found"
                            )
                    );

            if (product.getQuantity()
                    < itemRequest.quantity()) {

                throw new BadRequestException(
                        product.getName() + " out of stock"
                );
            }

            product.setQuantity(
                    product.getQuantity()
                            - itemRequest.quantity()
            );

            BigDecimal itemTotal =
                    product.getPrice().multiply(
                            BigDecimal.valueOf(
                                    itemRequest.quantity()
                            )
                    );

            totalPrice = totalPrice.add(itemTotal);

            OrderItem orderItem = OrderItem.builder()
                    .order(order)
                    .product(product)
                    .price(product.getPrice())
                    .quantity(itemRequest.quantity())
                    .build();

            order.getItems().add(orderItem);
        }

        order.setTotalPrice(totalPrice);

        return orderMapper.toResponse(
                orderRepository.save(order)
        );
    }
}
