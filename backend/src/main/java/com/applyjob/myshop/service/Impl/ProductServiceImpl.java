package com.applyjob.myshop.service.Impl;

import com.applyjob.myshop.dto.request.ProductRequest;
import com.applyjob.myshop.dto.response.ProductResponse;
import com.applyjob.myshop.entity.Product;
import com.applyjob.myshop.exception.ResourceNotFoundException;
import com.applyjob.myshop.mapper.ProductMapper;
import com.applyjob.myshop.repository.ProductRepository;
import com.applyjob.myshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
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
        // Chỉ tìm những sản phẩm có trạng thái là true
        Product product = productRepository
                .findByIdAndActiveTrue(id)
                .orElseThrow(() -> new ResourceNotFoundException("Khong tim thay san pham")
        );

        productMapper.updateProduct(product, request);

        return productMapper.toResponse(productRepository.save(product));
    }

    @Override
    public ProductResponse delete(String id) throws BadRequestException {
        // Chỉ tìm những sản phẩm có trạng thái là true
        Product product = productRepository
                .findByIdAndActiveTrue(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Khong tim thay san pham"
                        )
                );

        if (product.getQuantity() > 0) {
            throw new BadRequestException(
                    "Khong xoa san pham co so luong lon hon 0"
            );
        }

        product.setActive(false);

       return productMapper.toResponse(productRepository.save(product));
    }

    @Override
    public ProductResponse getById(String id) {
        // Chỉ tìm những sản phẩm có trạng thái là true
        Product product = productRepository
                .findByIdAndActiveTrue(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Khong tim thay san pham"
                        )
                );

        return productMapper.toResponse(product);
    }

    @Override
    public Page<ProductResponse> getAll(Pageable pageable) {
        // Chỉ tìm những sản phẩm có trạng thái là true
        return productRepository.findByActiveTrue(pageable)
                .map(productMapper::toResponse);
    }
}
