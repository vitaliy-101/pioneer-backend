package com.example.pioneerbackend.exceptions;

public class MultipartFileBytesIsNullException extends RuntimeException {
    public <T> MultipartFileBytesIsNullException() {
        super("Multipart file byte data is null");
    }

}
