package com.example.pioneerbackend.controller;

import com.example.pioneerbackend.dto.filter.ProductFilter;
import com.example.pioneerbackend.service.filter.FilterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/filter")
@RequiredArgsConstructor
public class FilterController {
    private final FilterService service;

    @PostMapping
    public void create(@RequestBody ProductFilter request) {
        service.create(request.getFilters());
    }

    @GetMapping
    public ProductFilter get() {
        return new ProductFilter(service.get());
    }
}
