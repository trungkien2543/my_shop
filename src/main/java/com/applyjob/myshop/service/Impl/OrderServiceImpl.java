package com.applyjob.myshop.service.Impl;

import com.applyjob.myshop.dto.request.OrderItemRequest;
import com.applyjob.myshop.dto.request.OrderRequest;
import com.applyjob.myshop.dto.response.OrderDetailResponse;
import com.applyjob.myshop.dto.response.OrderResponse;
import com.applyjob.myshop.entity.Order;
import com.applyjob.myshop.entity.OrderItem;
import com.applyjob.myshop.entity.Product;
import com.applyjob.myshop.exception.BadRequestException;
import com.applyjob.myshop.exception.ResourceNotFoundException;
import com.applyjob.myshop.mapper.OrderMapper;
import com.applyjob.myshop.repository.OrderRepository;
import com.applyjob.myshop.repository.ProductRepository;
import com.applyjob.myshop.service.OrderService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    ){

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
                                    "Không tìm thấy sản phẩm"
                            )
                    );

            if (product.getQuantity()
                    < itemRequest.quantity()) {

                throw new BadRequestException(
                        product.getName() + " không đủ số lượng hàng "
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

    @Override
    public Page<OrderResponse> getOrders(Pageable pageable) {

        Pageable finalPageable = pageable;

        if (pageable.getSort().isUnsorted()) {

            finalPageable = PageRequest.of(
                    pageable.getPageNumber(),
                    pageable.getPageSize(),
                    Sort.by(
                            Sort.Direction.DESC,
                            "createdAt"
                    )
            );
        }

        return orderRepository
                .findAll(finalPageable)
                .map(orderMapper::toResponse);
    }

    @Override
    public OrderDetailResponse getOrderDetail(String id) {
        Order order = orderRepository
                .findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Không tìm thấy đơn hàng"
                        )
                );

        return orderMapper.toDetailResponse(order);
    }

}
