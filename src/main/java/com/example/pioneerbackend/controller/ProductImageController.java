package com.example.pioneerbackend.controller;

import com.example.pioneerbackend.service.product.ProductImageService;
import com.example.pioneerbackend.util.FileUtils;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.core.io.Resource;

import static com.example.pioneerbackend.util.FileUtils.createLoadResponseEntity;

@RestController
@RequestMapping("api/v1/product/image")
@RequiredArgsConstructor
public class ProductImageController {
    private final ProductImageService productImageService;

    @Operation(description = "Получить фото продукта по id")
    @GetMapping("/{id}")
    public ResponseEntity<Resource> loadImage(@PathVariable("id") Long id) {
        var imageData = productImageService.findImageById(id);
        return createLoadResponseEntity(imageData);
    }
}
