package com.applyjob.myshop.controller.view.admin;

import com.applyjob.myshop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/orders")
public class AdminOrderController {

    private final OrderService orderService;

    @GetMapping
    public String list(
            @PageableDefault(size = 10)
            Pageable pageable,
            Model model
    ) {

        model.addAttribute(
                "page",
                orderService.getOrders(pageable)
        );

        return "admin/orders/list";
    }

    @GetMapping("/{id}")
    public String detail(
            @PathVariable String id,
            Model model
    ) {

        model.addAttribute(
                "order",
                orderService.getOrderDetail(id)
        );

        return "admin/orders/detail";
    }
}