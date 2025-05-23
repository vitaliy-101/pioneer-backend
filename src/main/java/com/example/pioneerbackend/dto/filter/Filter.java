package com.example.pioneerbackend.dto.filter;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Filter {
    private String filterType;
    private String key;
    private String type;
    private List<String> values;
}
