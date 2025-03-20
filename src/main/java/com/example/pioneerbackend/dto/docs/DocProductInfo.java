package com.example.pioneerbackend.dto.docs;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DocProductInfo {
    private String name;
    private String fileUrl;
}
