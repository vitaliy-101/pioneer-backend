package com.example.pioneerbackend.dto.docs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DocsCreateRequest {
    private Long productId;
    private DocType type;
}
