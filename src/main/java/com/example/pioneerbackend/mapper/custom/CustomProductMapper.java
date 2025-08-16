package com.example.pioneerbackend.mapper.custom;

import com.example.pioneerbackend.dto.ProductAllResponse;
import com.example.pioneerbackend.dto.docs.DocProductInfo;
import com.example.pioneerbackend.dto.manufacturer.ManufacturerInfo;
import com.example.pioneerbackend.dto.product.MeasuringRanges;
import com.example.pioneerbackend.dto.product.ProductCreationRequest;
import com.example.pioneerbackend.dto.product.ProductCreationResponse;
import com.example.pioneerbackend.dto.product.ProductFullResponse;
import com.example.pioneerbackend.entity.basket.Basket;
import com.example.pioneerbackend.entity.manufacturer.Manufacturer;
import com.example.pioneerbackend.entity.product.Product;
import com.example.pioneerbackend.mapper.ManufacturerMapper;
import com.example.pioneerbackend.mapper.ProductMapper;
import com.example.pioneerbackend.util.JsonUtils;
import com.example.pioneerbackend.util.ProductUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

import static com.example.pioneerbackend.util.ProductUtils.*;

@Component
@RequiredArgsConstructor
public class CustomProductMapper {
    private final ProductMapper productMapper;
    private final ManufacturerMapper manufacturerMapper;

    public Product fromCreationRequestToEntity(ProductCreationRequest request, Manufacturer manufacturer) {
        var product = productMapper.fromCreationRequestCharacteristicsToEntity(request.getProductCharacteristics());
        setMainParamsFromRequest(request, product);
        product.setManufacturer(manufacturer);
        return product;
    }

    public ProductCreationResponse fromEntityToCreationResponse(Product product) {
        var productCharacteristics = productMapper.fromEntityToProductCharacteristics(product);
        var measuringRanges = productMapper.fromEntityToMeasuringRanges(product);
        var response = productMapper.fromEntityToProductCreationResponse(product);
        response.setProductCharacteristics(productCharacteristics);
        response.setMeasuringRanges(measuringRanges);
        response.setManufacturerId(getManufacturerId(product));
        return response;
    }

    private Long getManufacturerId(Product product) {
        if (product.getManufacturer() == null) {
            return null;
        }
        return product.getManufacturer().getId();
    }

    public ProductFullResponse fromEntityToProductFullResponse(Product product,
                                                               List<DocProductInfo> docProductInfos,
                                                               Basket basket) {
        var productFullResponse = productMapper.fromCreationResponseToFullResponse(fromEntityToCreationResponse(product), extractBasketCount(basket));
        productFullResponse.setImagesUrl(extractImages(product));
        productFullResponse.setDocProductInfos(docProductInfos);
        productFullResponse.setManufacturerInfo(extractManufactureInfo(product));
        return productFullResponse;
    }

    private ManufacturerInfo extractManufactureInfo(Product product) {
        if (product.getManufacturer() == null) {
            return null;
        }
        return manufacturerMapper.toManufacturerInfo(product.getManufacturer());
    }

    public ProductAllResponse fromPageProductToResponse(Page<Product> products, Map<Long, Integer> productCountsMap, Integer total) {
        return new ProductAllResponse(products.stream()
                .map(product -> productMapper.fromEntityToShortResponse(product, extractProductCountFromMap(productCountsMap, product.getId())))
                .toList(), total);
    }

    private void setMainParamsFromRequest(ProductCreationRequest request, Product product) {
        product.setPrice(request.getPrice());
        product.setDescription(request.getDescription());
        product.setTitle(request.getTitle());
        if (request.getMeasuringRanges() != null) setMeasureRangesFromRequest(request.getMeasuringRanges(), product);
    }

    private void setMeasureRangesFromRequest(MeasuringRanges measuringRanges, Product product) {
        //todo отдельный метод в product mapper
        if (measuringRanges.getParams() != null) {
            product.setMeasuringRanges(measuringRanges.getParams().stream()
                    .map(JsonUtils::convertToString)
                    .toList());
        }
    }

}
