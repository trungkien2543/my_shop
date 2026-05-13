package com.applyjob.myshop.service.Impl;

import com.applyjob.myshop.dto.request.ProductRequest;
import com.applyjob.myshop.dto.response.ProductResponse;
import com.applyjob.myshop.entity.Product;
import com.applyjob.myshop.mapper.ProductMapper;
import com.applyjob.myshop.repository.ProductRepository;
import com.applyjob.myshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final ProductMapper productMapper;


    @Override
    public ProductResponse create(ProductRequest request) {

        Product newProduct = productMapper.toEntity(request);

        return productMapper.toResponse(productRepository.save(newProduct));
    }

    @Override
    public ProductResponse update(String id, ProductRequest request) {
        return null;
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public ProductResponse getById(String id) {
        return null;
    }

    @Override
    public Page<ProductResponse> getAll(Pageable pageable) {
        return productRepository.findAll(pageable)
                .map(productMapper::toResponse);
    }
}
