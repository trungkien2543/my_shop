package com.applyjob.myshop.service.Impl;

import com.applyjob.myshop.dto.request.ProductRequest;
import com.applyjob.myshop.dto.response.ProductDetailResponse;
import com.applyjob.myshop.dto.response.ProductResponse;
import com.applyjob.myshop.entity.Product;
import com.applyjob.myshop.exception.BadRequestException;
import com.applyjob.myshop.exception.ResourceNotFoundException;
import com.applyjob.myshop.mapper.ProductMapper;
import com.applyjob.myshop.repository.ProductRepository;
import com.applyjob.myshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.PageRequest;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final ProductMapper productMapper;


    @Override
    @Transactional
    public ProductResponse create(ProductRequest request) {

        Product newProduct = productMapper.toEntity(request);

        return productMapper.toResponse(productRepository.save(newProduct));
    }

    @Override
    @Transactional
    public ProductResponse update(String id, ProductRequest request) {
        // Chỉ tìm những sản phẩm có trạng thái là true
        Product product = productRepository
                .findByIdAndActiveTrue(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy sản phẩm")
        );

        productMapper.updateProduct(product, request);

        return productMapper.toResponse(productRepository.save(product));
    }

    @Override
    @Transactional
    public ProductResponse delete(String id) {
        // Chỉ tìm những sản phẩm có trạng thái là true
        Product product = productRepository
                .findByIdAndActiveTrue(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Không tìm thấy sản phẩm"
                        )
                );

        if (product.getQuantity() > 0) {
            throw new BadRequestException(
                    "Không xóa sản phẩm có số lượng lớn hơn 0"
            );
        }

        product.setActive(false);

       return productMapper.toResponse(productRepository.save(product));
    }

    @Override
    public ProductDetailResponse getById(String id) {
        // Chỉ tìm những sản phẩm có trạng thái là true
        Product product = productRepository
                .findByIdAndActiveTrue(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Không tìm thấy sản phẩm"
                        )
                );

        return productMapper.toDetailResponse(product);
    }

    @Override
    public Page<ProductResponse> getAll(Pageable pageable) {
        // Chỉ tìm những sản phẩm có trạng thái là true
        Pageable finalPageable = pageable;

        if (pageable.getSort().isUnsorted()) {

            finalPageable = PageRequest.of(
                    pageable.getPageNumber(),
                    pageable.getPageSize(),
                    Sort.by(
                            Sort.Direction.DESC,
                            "updatedAt"
                    )
            );
        }

        return productRepository.findByActiveTrue(finalPageable).map(productMapper::toResponse);
    }
}
