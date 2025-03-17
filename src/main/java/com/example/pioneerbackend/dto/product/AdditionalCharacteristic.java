package com.example.pioneerbackend.dto.product;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdditionalCharacteristic {
    @Schema(description = "Название добавляемого поля")
    private String key;

    @Schema(description = "Значение добавляемого поля")
    private String value;
}
