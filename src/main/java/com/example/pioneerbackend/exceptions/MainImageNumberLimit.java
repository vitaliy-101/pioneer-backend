package com.example.pioneerbackend.exceptions;

import static java.lang.String.format;

public class MainImageNumberLimit extends RuntimeException {
    public <T> MainImageNumberLimit(Integer mainImageNumber, Integer limit) {
        super(format("Number = %d for main image more then image list size = %d", mainImageNumber, limit));
    }

}
