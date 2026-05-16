package com.applyjob.myshop.controller.view.user;

import com.applyjob.myshop.dto.request.OrderRequest;
import com.applyjob.myshop.service.OrderService;
import com.applyjob.myshop.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;

@Controller
@RequiredArgsConstructor
public class UserProductController {

    private final ProductService productService;

    private final OrderService orderService;

    @GetMapping({"/user/products", "/"})
    public String products(Model model) {

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
                        new ArrayList<>()
                )
        );

        return "user/products";
    }

    @PostMapping("user/orders/create")
    public String createOrder(
            @Valid @ModelAttribute OrderRequest request,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes
    ) {

        if (bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute(
                    "error",
                    bindingResult.getFieldError()
                            .getDefaultMessage()
            );

            return "redirect:/user/products";
        }

        try {

            orderService.createOrder(request);

            redirectAttributes.addFlashAttribute(
                    "success",
                    "Create order successfully"
            );

        } catch (Exception e) {

            redirectAttributes.addFlashAttribute(
                    "error",
                    e.getMessage()
            );
        }

        return "redirect:/user/products";
    }
}