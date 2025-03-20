package com.example.pioneerbackend.dto.manufacturer;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ManufacturerCreateRequest {
    @Schema(description = "Название производителя")
    private String name;
}
