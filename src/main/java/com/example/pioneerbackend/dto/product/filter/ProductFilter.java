package com.example.pioneerbackend.dto.product.filter;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductFilter {
    private List<Filter> filters;
}
