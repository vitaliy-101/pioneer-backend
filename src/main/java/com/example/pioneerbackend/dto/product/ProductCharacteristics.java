package com.example.pioneerbackend.dto.product;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductCharacteristics {
    @Schema(description = "Рабочая температура")
    private String operatingTemperature;

    @Schema(description = "Степень защиты")
    private String degreeProtection;

    @Schema(description = "Наличие предупредительной сигнализации")
    private String warningAlarm;

    @Schema(description = "Наличие дисплея")
    private String display;

    @Schema(description = "Калибровка")
    private String calibration;

    @Schema(description = "Время отклика продукта в миллисекундах")
    private String responseTime;

    @Schema(description = "Степень взрывозащиты")
    private String explosionProtectionRating;

    @Schema(description = "Источник питания")
    private String powerSupply;

    @Schema(description = "Рабочая влажность")
    private String operatingHumidity;

    @Schema(description = "Тип сенсоров")
    private String sensorType;

    @Schema(description = "Метод отбора проб газа")
    private String gasSampling;

    @Schema(description = "Корпус")
    private String enclosure;

    @Schema(description = "Время работы продукта в часах")
    private String operatingTime;

    @Schema(description = "Время зарядки в часах")
    private String chargingTime;

    @Schema(description = "Тип каналов")
    private String channelType;

    @Schema(description = "Тип прибора", example = "Стационарный")
    private String type;

    @Schema(description = "Дополнительные характеристики ключ - значение")
    private List<AdditionalCharacteristic> additionalCharacteristics;
}
