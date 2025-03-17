package com.example.pioneerbackend.dto.basket;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BasketCreateRequest {
    @Schema(description = "Количество товаров")
    private Integer count;
    @Schema(description = "Id товара")
    private Long productId;
}
