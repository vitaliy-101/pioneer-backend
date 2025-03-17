package com.example.pioneerbackend.dto.product;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Сущность запроса создания товара")
@Builder
public class ProductCreationRequest {
    @Schema(description = "Цена товара")
    private Double price;

    @Schema(description = "Описание товара")
    private String description;

    @Schema(description = "Краткое название товара")
    private String title;

    @Schema(description = "Характеристики товара")
    private ProductCharacteristics productCharacteristics;

    @Schema(description = "Диапазоны измерений")
    private MeasuringRanges measuringRanges;
}
