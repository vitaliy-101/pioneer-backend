package com.example.pioneerbackend.dto.product.filter;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductFilterRequest {
    @Schema(description = "Тип прибора, по дефолту - все", example = "cтационарный")
    private String type;

    @Schema(description = "Тип каналов по дефолту - все", example = "одноканальный")
    private String channelType;

    @Schema(description = "Тип измеряемых газов")
    private List<String> measuredGasesTypes;

    @Schema(description = "Беспроводная связь")
    private List<String> wirelessLinkList;

    @Schema(description = "Встроенный насос")
    private List<String> integratedPumpList;

    @Schema(description = "Нижная граница стоимости")
    private Double minPrice;

    @Schema(description = "Верхняя граница стоимости")
    private Double maxPrice;

    @Schema(description = "Как сортировка?", example = "priceAsc")
    private String sortBy;

}
