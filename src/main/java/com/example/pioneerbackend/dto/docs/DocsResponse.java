package com.example.pioneerbackend.dto.docs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class DocsResponse {
    private List<DocInfo> docs;
}
