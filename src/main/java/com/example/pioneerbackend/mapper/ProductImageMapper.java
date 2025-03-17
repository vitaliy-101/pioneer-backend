package com.example.pioneerbackend.mapper;

import com.example.pioneerbackend.entity.product.Product;
import com.example.pioneerbackend.entity.product.ProductImage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductImageMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "image", source = "bytes")
    @Mapping(target = "product", source = "product")
    ProductImage convertFromByteToEntity(byte[] bytes, Product product);

}
