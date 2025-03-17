package com.example.pioneerbackend.dto.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.core.io.InputStreamResource;

@Getter
@Setter
@AllArgsConstructor
public class ProductImageData {
    private InputStreamResource resource;
    private String contentType;
}
