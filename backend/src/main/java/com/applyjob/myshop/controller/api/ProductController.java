package com.applyjob.myshop.controller.api;

import com.applyjob.myshop.common.Endpoint;
import com.applyjob.myshop.dto.request.ProductRequest;
import com.applyjob.myshop.dto.response.ApiResponse;
import com.applyjob.myshop.dto.response.ProductResponse;
import com.applyjob.myshop.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Endpoint.Api.ROOT+Endpoint.Api.PRODUCTS)
@RequiredArgsConstructor
public class ProductController extends BaseController{

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<ApiResponse<Page<ProductResponse>>> getAllProducts(
            Pageable pageable
    ) {
        return createSuccessResponse(productService.getAll(pageable));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ProductResponse>> createProduct(
            @Valid @RequestBody ProductRequest productRequest
            ) {
        return createSuccessResponse(productService.create(productRequest));
    }
}
