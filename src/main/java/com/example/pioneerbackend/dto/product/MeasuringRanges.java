package com.example.pioneerbackend.dto.product;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MeasuringRanges {
    @Schema(description = "Параметры ключ-значение")
    private List<AdditionalCharacteristic> params;
}
