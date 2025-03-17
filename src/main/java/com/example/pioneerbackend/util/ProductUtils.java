package com.example.pioneerbackend.util;

import com.example.pioneerbackend.entity.basket.Basket;
import com.example.pioneerbackend.entity.product.Product;
import com.example.pioneerbackend.entity.product.ProductImage;
import lombok.experimental.UtilityClass;
import org.springframework.data.domain.Page;

import java.util.*;
import java.util.stream.Collectors;

import static com.example.pioneerbackend.constant.Urls.PRODUCT_IMAGE_URL;
import static com.example.pioneerbackend.util.FileUtils.convertFileToUrl;

@UtilityClass
public class ProductUtils {
    public static List<String> extractImages(Product product) {
        return product.getImages().stream()
                .sorted(Comparator.comparing(ProductImage::isMain).reversed())
                .map(productImage -> convertFileToUrl(PRODUCT_IMAGE_URL, productImage.getId()))
                .collect(Collectors.toList());
    }

    public static List<Long> extractProductIds(Page<Product> products) {
        if (products == null || products.isEmpty()) {
            return new ArrayList<>();
        }
        return products.stream()
                .map(Product::getId)
                .toList();
    }

    public static Map<Long, Integer> mapBasketsToMapProductCounts(List<Basket> baskets) {
        if (baskets == null || baskets.isEmpty()) {
            return new HashMap<>();
        }
        return baskets.stream()
                .collect(Collectors.toMap(
                        basket -> basket.getProduct().getId(),
                        Basket::getCount,
                        Integer::sum));
    }

    public static Integer extractProductCountFromMap(Map<Long, Integer> productCountsMap, Long productId) {
        if (!productCountsMap.containsKey(productId)) {
            return 0;
        }
        return productCountsMap.get(productId);
    }


    public static Integer extractBasketCount(Basket basket) {
        if (basket == null)
            return 0;
        return basket.getCount();
    }


}
