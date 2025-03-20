package com.example.pioneerbackend.mapper.custom;

import com.example.pioneerbackend.dto.ProductAllResponse;
import com.example.pioneerbackend.dto.docs.DocProductInfo;
import com.example.pioneerbackend.dto.product.MeasuringRanges;
import com.example.pioneerbackend.dto.product.ProductCreationRequest;
import com.example.pioneerbackend.dto.product.ProductCreationResponse;
import com.example.pioneerbackend.dto.product.ProductFullResponse;
import com.example.pioneerbackend.entity.basket.Basket;
import com.example.pioneerbackend.entity.product.Product;
import com.example.pioneerbackend.mapper.ProductMapper;
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

    public Product fromCreationRequestToEntity(ProductCreationRequest request) {
        var product = productMapper.fromCreationRequestCharacteristicsToEntity(request.getProductCharacteristics());
        setMainParamsFromRequest(request, product);
        return product;
    }

    public ProductCreationResponse fromEntityToCreationResponse(Product product) {
        var productCharacteristics = productMapper.fromEntityToProductCharacteristics(product);
        var measuringRanges = productMapper.fromEntityToMeasuringRanges(product);
        var response = productMapper.fromEntityToProductCreationResponse(product);
        response.setProductCharacteristics(productCharacteristics);
        response.setMeasuringRanges(measuringRanges);
        return response;
    }

    public ProductFullResponse fromEntityToProductFullResponse(Product product,
                                                               List<DocProductInfo> docProductInfos,
                                                               Basket basket) {
        var productFullResponse = productMapper.fromCreationResponseToFullResponse(fromEntityToCreationResponse(product), extractBasketCount(basket));
        productFullResponse.setImagesUrl(extractImages(product));
        productFullResponse.setDocProductInfos(docProductInfos);
        return productFullResponse;
    }

    public ProductAllResponse fromPageProductToResponse(Page<Product> products, Map<Long, Integer> productCountsMap) {
        return new ProductAllResponse(products.stream()
                .map(product -> productMapper.fromEntityToShortResponse(product, extractProductCountFromMap(productCountsMap, product.getId())))
                .toList());
    }

    private void setMainParamsFromRequest(ProductCreationRequest request, Product product) {
        product.setPrice(request.getPrice());
        product.setDescription(request.getDescription());
        product.setTitle(request.getTitle());
        if (request.getMeasuringRanges() != null) setMeasureRangesFromRequest(request.getMeasuringRanges(), product);
    }

    private void setMeasureRangesFromRequest(MeasuringRanges measuringRanges, Product product) {
        product.setCombustibleGases(measuringRanges.getCombustibleGases());
        product.setOxygen(measuringRanges.getOxygen());
        product.setCarbonMonoxide(measuringRanges.getCarbonMonoxide());
        product.setHydrogenSulphide(measuringRanges.getHydrogenSulphide());
    }

}
