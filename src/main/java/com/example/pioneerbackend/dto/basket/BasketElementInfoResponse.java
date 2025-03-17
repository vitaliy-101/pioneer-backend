package com.example.pioneerbackend.dto.basket;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BasketElementInfoResponse {
    @Schema(description = "Id товара корзины")
    private Long id;
    @Schema(description = "Id товара")
    private Long productId;
    @Schema(description = "Количество товара")
    private Integer count;
    @Schema(description = "Цена товара")
    private Double price;
    @Schema(description = "Краткое название товара")
    private String title;
    @Schema(description = "Аватарка товара")
    private String imageUrl;
}
