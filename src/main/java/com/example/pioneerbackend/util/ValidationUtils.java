package com.example.pioneerbackend.util;

import com.example.pioneerbackend.exceptions.MainImageNumberLimit;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ValidationUtils {

    public static Integer validateMainImageNumber(Integer mainImageNumber, Integer maxValue) {
        if (mainImageNumber - 1 < maxValue) {
            return mainImageNumber - 1;
        }
        throw new MainImageNumberLimit(mainImageNumber, maxValue);
    }
}
