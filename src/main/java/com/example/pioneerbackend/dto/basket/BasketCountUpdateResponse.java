package com.example.pioneerbackend.dto.basket;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BasketCountUpdateResponse {
    @Schema(description = "Количество товара")
    private Integer count;
}
