package com.example.pioneerbackend.mapper;

import com.example.pioneerbackend.dto.basket.BasketElementInfo;
import com.example.pioneerbackend.dto.basket.BasketElementInfoResponse;
import com.example.pioneerbackend.entity.basket.Basket;
import com.example.pioneerbackend.entity.product.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;

import java.util.List;

import static com.example.pioneerbackend.constant.Urls.PRODUCT_IMAGE_URL;
import static com.example.pioneerbackend.util.FileUtils.convertToUrl;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface BasketMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "product", source = "product")
    @Mapping(target = "count", source = "count")
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    Basket toEntity(Integer count, Product product);

    @Mapping(target = "imageUrl", source = "imageId", qualifiedByName = "convertImageToUrl")
    @Mapping(target = "total", source = ".", qualifiedByName = "calculateTotal")
    BasketElementInfoResponse toInfoResponse(BasketElementInfo basketElementInfo);

    List<BasketElementInfoResponse> toInfoResponseList(List<BasketElementInfo> basketElementInfoList);

    @Named("calculateTotal")
    default Double calculateTotal(BasketElementInfo basketElementInfo) {
        return basketElementInfo.getPrice() * basketElementInfo.getCount();
    }

    @Named("convertImageToUrl")
    default String convertImageToUrl(Long imageId) {
        return convertToUrl(PRODUCT_IMAGE_URL, imageId.toString());
    }
}
