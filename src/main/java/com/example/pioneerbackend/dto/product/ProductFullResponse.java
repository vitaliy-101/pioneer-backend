package com.example.pioneerbackend.dto.product;

import com.example.pioneerbackend.dto.docs.DocProductInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductFullResponse {
    @Schema(description = "ID товара")
    private Long id;

    @Schema(description = "Цена товара")
    private Double price;

    @Schema(description = "Описание товара")
    private String description;

    @Schema(description = "Краткое название товара")
    private String title;

    @Schema(description = "Колисечтво товаров в корзине")
    private Integer count = 0;

    @Schema(description = "Характеристики товара")
    private ProductCharacteristics productCharacteristics;

    @Schema(description = "Диапазоны измерений")
    private MeasuringRanges measuringRanges;

    @Schema(description = "Фотографии товара")
    private List<String> imagesUrl;

    @Schema(description = "Документы товара")
    private List<DocProductInfo> docProductInfos;
}
