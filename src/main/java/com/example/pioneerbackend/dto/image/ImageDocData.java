package com.example.pioneerbackend.dto.image;

import lombok.Getter;
import lombok.Setter;
import org.springframework.core.io.InputStreamResource;

@Getter
@Setter
public class ImageDocData extends ImageData {
    private String name;
    public ImageDocData(InputStreamResource resource, String contentType) {
        super(resource, contentType);
    }
}
