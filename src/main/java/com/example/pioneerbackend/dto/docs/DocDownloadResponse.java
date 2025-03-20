package com.example.pioneerbackend.dto.docs;

import com.example.pioneerbackend.dto.image.ImageData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DocDownloadResponse {
    private String name;
    private ImageData imageData;
}
