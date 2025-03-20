package com.example.pioneerbackend.dto.docs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DocInfo {
    private String name;
    private String type;
    private String docUrl;
}
