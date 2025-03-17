package com.example.pioneerbackend.util;

import com.example.pioneerbackend.dto.product.AdditionalCharacteristic;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.experimental.UtilityClass;

@UtilityClass
public class JsonUtils {
    private final ObjectMapper objectMapper = new ObjectMapper();

    public static String convertFromAdditionalCharacteristicToString(AdditionalCharacteristic characteristic) {
        try {
            return objectMapper.writeValueAsString(characteristic);
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при конвертации характеристик в JSON", e); //todo
        }
    }

    public static AdditionalCharacteristic convertFromStringToAdditionalCharacteristic(String characteristic) {
        try {
            return objectMapper.readValue(characteristic, AdditionalCharacteristic.class);
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при конвертации характеристик в AdditionalCharacteristic", e); //todo
        }
    }
}
