package com.example.pioneerbackend.controller;

import com.example.pioneerbackend.service.product.ProductImageService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.core.io.Resource;

@RestController
@RequestMapping("api/v1/product/image")
@RequiredArgsConstructor
public class ProductImageController {
    private final ProductImageService productImageService;

    @Operation(description = "Получить фото продукта по id")
    @GetMapping("/{id}")
    public ResponseEntity<Resource> loadImage(@PathVariable("id") Long id) {
        var productImageData = productImageService.findImageById(id);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(productImageData.getContentType()))
                .body(productImageData.getResource());
    }
}
