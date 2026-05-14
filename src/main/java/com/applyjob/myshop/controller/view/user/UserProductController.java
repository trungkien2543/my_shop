package com.applyjob.myshop.controller.view.user;

import com.applyjob.myshop.dto.request.OrderRequest;
import com.applyjob.myshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user/products")
public class UserProductController {

    private final ProductService productService;

    @GetMapping
    public String products(
            Model model
    ) {

        model.addAttribute(
                "products",
                productService.getAll()
        );

        model.addAttribute(
                "order",
                new OrderRequest(
                        "",
                        "",
                        "",
                        List.of()
                )
        );

        return "user/products";
    }
}