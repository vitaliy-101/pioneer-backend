package com.example.pioneerbackend.dto.docs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DocImageInfo {
    private String name;
    private byte[] data;
}
