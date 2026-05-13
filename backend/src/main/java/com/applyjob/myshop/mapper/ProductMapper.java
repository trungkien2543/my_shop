package com.applyjob.myshop.mapper;

import com.applyjob.myshop.dto.request.ProductRequest;
import com.applyjob.myshop.dto.response.ProductResponse;
import com.applyjob.myshop.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product toEntity(ProductRequest request);

    ProductResponse toResponse(Product product);
}
