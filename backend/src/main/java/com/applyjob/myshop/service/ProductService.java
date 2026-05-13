package com.applyjob.myshop.service;

import com.applyjob.myshop.dto.request.ProductRequest;
import com.applyjob.myshop.dto.response.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {

    ProductResponse create(ProductRequest request);

    ProductResponse update(String id, ProductRequest request);

    void delete(String id);

    ProductResponse getById(String id);

    Page<ProductResponse> getAll(Pageable pageable);
}
