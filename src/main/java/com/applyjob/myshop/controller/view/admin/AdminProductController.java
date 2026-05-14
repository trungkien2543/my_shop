package com.applyjob.myshop.controller.view.admin;

import com.applyjob.myshop.dto.request.ProductRequest;
import com.applyjob.myshop.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/products")
public class AdminProductController {
    private final ProductService productService;

    @GetMapping
    public String list(
            @PageableDefault(size = 10) Pageable pageable,
            Model model
    ) {

        model.addAttribute(
                "page",
                productService.getPageProduct(pageable)
        );

        return "admin/products/list";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("product", new ProductRequest("", BigDecimal.ZERO,0,""));
        return "admin/products/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute ProductRequest request) {
        productService.create(request);
        return "redirect:/admin/products";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(
            @PathVariable String id,
            Model model
    ) {

        model.addAttribute("product", productService.getById(id));

        return "admin/products/edit";
    }

    @PostMapping("/edit/{id}")
    public String updateProduct(
            @PathVariable String id,
            @Valid @ModelAttribute("product") ProductRequest request,
            BindingResult bindingResult
    ) {

        if (bindingResult.hasErrors()) {
            return "admin/products/edit";
        }

        productService.update(id, request);

        return "redirect:/admin/products";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable String id) {
        productService.delete(id);
        return "redirect:/admin/products";
    }

    @GetMapping("/{id}")
    public String productDetail(
            @PathVariable String id,
            Model model
    ) {

        model.addAttribute("product", productService.getById(id));

        return "admin/products/detail";
    }
}
