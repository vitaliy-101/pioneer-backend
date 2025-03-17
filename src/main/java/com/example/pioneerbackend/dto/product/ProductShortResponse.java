package com.example.pioneerbackend.dto.product;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductShortResponse {
    @Schema(description = "ID товара")
    private Long id;

    @Schema(description = "Цена товара")
    private Double price;

    @Schema(description = "Краткое название товара")
    private String title;

    @Schema(description = "Количество товара в корзине")
    private Integer count;

    @Schema(description = "Аватарка товара")
    private String imageUrl;
}
