package com.example.pioneerbackend.dto.product;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MeasuringRanges {
    @Schema(description = "Горючие газы")
    private String combustibleGases;

    @Schema(description = "Кислород")
    private String oxygen;

    @Schema(description = "Угарный газ")
    private String carbonMonoxide;

    @Schema(description = "Сероводород")
    private String hydrogenSulphide;
}
