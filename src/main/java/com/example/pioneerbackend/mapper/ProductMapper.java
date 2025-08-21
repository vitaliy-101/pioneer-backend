package com.example.pioneerbackend.mapper;

import com.example.pioneerbackend.dto.product.*;
import com.example.pioneerbackend.entity.basket.Basket;
import com.example.pioneerbackend.entity.product.Product;
import com.example.pioneerbackend.entity.product.ProductImage;
import com.example.pioneerbackend.util.JsonUtils;
import com.example.pioneerbackend.util.ProductUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;

import java.util.List;

import static com.example.pioneerbackend.util.JsonUtils.convertFromString;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductMapper {

    @Mapping(target = "additional", source = "additionalCharacteristics", qualifiedByName = "convertAdditionalToStringList")
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    Product fromCreationRequestCharacteristicsToEntity(ProductCharacteristics characteristics);

    @Mapping(target = "additionalCharacteristics", source = "additional", qualifiedByName = "convertStringListToAdditionalCharacteristics")
    ProductCharacteristics fromEntityToProductCharacteristics(Product product);

    @Mapping(target = "params", source = "measuringRanges", qualifiedByName = "convertStringListToAdditionalCharacteristics")
    MeasuringRanges fromEntityToMeasuringRanges(Product product);

    @Mapping(target = "measuringRanges", ignore = true)
    ProductCreationResponse fromEntityToProductCreationResponse(Product product);

    @Mapping(target = "count", source = "count")
    ProductFullResponse fromCreationResponseToFullResponse(ProductCreationResponse creationResponse, Integer count);

    @Mapping(target = "count", source = "count")
    @Mapping(target = "imageUrl", source = "product", qualifiedByName = "extractMainImage")
    ProductShortResponse fromEntityToShortResponse(Product product, Integer count);

    @Named("extractMainImage")
    default String extractMainImage(Product product) {
        if (product.getImages() == null) {
            return "";
        }
        var images = ProductUtils.extractImages(product);
        if (images == null || images.isEmpty()) {
            return "";
        }
        return ProductUtils.extractImages(product).getFirst();
    }

    @Named("convertAdditionalToStringList")
    default List<String> convertAdditionalToStringList(List<AdditionalCharacteristic> additionalCharacteristics) {
        if (additionalCharacteristics == null) return null;
        return additionalCharacteristics.stream()
                .map(JsonUtils::convertToString)
                .toList();
    }


    @Named("convertStringListToAdditionalCharacteristics")
    default List<AdditionalCharacteristic> convertStringListToAdditionalCharacteristics(List<String> additionalList) {
        if (additionalList == null) return null;
        return additionalList.stream()
                .map(element -> convertFromString(element, AdditionalCharacteristic.class))
                .toList();
    }
}
