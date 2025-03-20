package com.example.pioneerbackend.dto.image;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.core.io.InputStreamResource;

@Getter
@Setter
@AllArgsConstructor
public class ImageData {
    private InputStreamResource resource;
    private String contentType;
}
