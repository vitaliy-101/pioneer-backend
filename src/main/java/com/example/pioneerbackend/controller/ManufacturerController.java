package com.example.pioneerbackend.controller;

import com.example.pioneerbackend.dto.manufacturer.ManufacturerCreateRequest;
import com.example.pioneerbackend.dto.manufacturer.ManufacturerGetResponse;
import com.example.pioneerbackend.service.manufacturer.ManufacturerService;
import com.example.pioneerbackend.util.FileUtils;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.example.pioneerbackend.util.FileUtils.createLoadResponseEntity;

@RestController
@RequestMapping("api/v1/manufacturer")
@RequiredArgsConstructor
public class ManufacturerController {
    private final ManufacturerService service;

    @PostMapping
    public void create(@RequestPart("request")  ManufacturerCreateRequest request,
                       @RequestPart("image") MultipartFile image) {
        service.create(request, image);
    }

    @PutMapping("/{id}")
    public void update(@RequestPart("request")  ManufacturerCreateRequest request,
                       @RequestPart("image") MultipartFile image,
                       @PathVariable("id") Long id) {
        service.update(request, image, id);
    }

    @GetMapping
    public ManufacturerGetResponse findAll() {
        return new ManufacturerGetResponse(service.findAll());
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        service.deleteById(id);
    }

    @Operation(description = "Получить фото производителя по названию (id)")
    @GetMapping("/{id}")
    public ResponseEntity<Resource> loadImage(@PathVariable("id") Long id) {
        var imageData = service.findImageById(id);
        return createLoadResponseEntity(imageData);
    }

}
