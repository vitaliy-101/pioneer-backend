package com.example.pioneerbackend.dto.basket;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class BasketGetResponse {
    @Schema(description = "Товары в корзине")
    private List<BasketElementInfoResponse> basketElementInfoResponseList;
}
