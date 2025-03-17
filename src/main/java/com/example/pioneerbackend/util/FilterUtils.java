package com.example.pioneerbackend.util;

import lombok.experimental.UtilityClass;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@UtilityClass
public class FilterUtils {
    public static Object convertTypeValue(String value, String type) {
        if (value == null || type == null) {
            throw new IllegalArgumentException("Value and type cannot be null");
        }
        var trimmedValue = value.trim();
        try {
            return switch (type) {
                case "string" -> trimmedValue;
                case "double" -> Double.valueOf(trimmedValue);
                case "integer" -> Integer.valueOf(trimmedValue);
                case "long" -> Long.valueOf(trimmedValue);
                case "boolean" -> Boolean.valueOf(trimmedValue);
                case "bigDecimal" -> new BigDecimal(trimmedValue);
                case "localDate" -> LocalDate.parse(trimmedValue);
                case "localDateTime" -> LocalDateTime.parse(trimmedValue);
                default -> throw new IllegalArgumentException("Unsupported type: " + type);
            };
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid value for type " + type + ": " + value, e);
        }
    }


}
