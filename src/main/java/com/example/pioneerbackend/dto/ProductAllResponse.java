package com.example.pioneerbackend.dto;

import com.example.pioneerbackend.dto.product.ProductShortResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ProductAllResponse {

    @Schema(description = "Страница продукта")
    private List<ProductShortResponse> products;
}
