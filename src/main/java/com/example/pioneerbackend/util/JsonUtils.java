package com.example.pioneerbackend.util;

import com.example.pioneerbackend.dto.product.AdditionalCharacteristic;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.experimental.UtilityClass;

@UtilityClass
public class JsonUtils {
    private final ObjectMapper objectMapper = new ObjectMapper();

    public static <T> String convertToString(T object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при конвертации объекта в JSON", e);
        }
    }

    public static <T> T convertFromString(String json, Class<T> clazz) {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при конвертации JSON в объект " + clazz.getSimpleName(), e);
        }
    }
}
