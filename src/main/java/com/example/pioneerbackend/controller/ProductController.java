package com.example.pioneerbackend.controller;

import com.example.pioneerbackend.dto.ProductAllResponse;
import com.example.pioneerbackend.dto.product.ProductCreationRequest;
import com.example.pioneerbackend.dto.product.ProductCreationResponse;
import com.example.pioneerbackend.dto.product.ProductFullResponse;
import com.example.pioneerbackend.dto.filter.ProductFilter;
import com.example.pioneerbackend.entity.user.User;
import com.example.pioneerbackend.mapper.custom.CustomProductMapper;
import com.example.pioneerbackend.service.basket.BasketService;
import com.example.pioneerbackend.service.docs.DocsService;
import com.example.pioneerbackend.service.product.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.example.pioneerbackend.util.ProductUtils.*;
import static com.example.pioneerbackend.util.UserUuidUtils.silenceIds;

@RestController
@RequestMapping("api/v1/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final BasketService basketService;
    private final DocsService docsService;
    private final CustomProductMapper productMapper;

    @Operation(description = "Создать новый товар")
    @PostMapping
    public ProductCreationResponse createProduct(@RequestPart("request") ProductCreationRequest request,
                                                 @RequestPart(value = "images", required = false)
                                                 List<MultipartFile> images) {
        return productMapper.fromEntityToCreationResponse(
                productService.createProduct(
                        productMapper.fromCreationRequestToEntity(request),
                        images
                )
        );
    }

    @Operation(description = "Создать новый товар")
    @PutMapping("/{id}")
    public ProductCreationResponse update(@RequestBody ProductCreationRequest request,
                                          @PathVariable("id") Long id) {
        return productMapper.fromEntityToCreationResponse(productService.update(id, request));
    }

    @Operation(description = "Получить товар по id")
    @GetMapping("/{id}")
    public ProductFullResponse findProductById(@PathVariable("id") Long id,
                                               @AuthenticationPrincipal User user,
                                               @RequestHeader(value = "Guest-UUID", required = false) String uuid) {
        return productMapper.fromEntityToProductFullResponse(
                productService.findProductById(id),
                docsService.findDocsByProductId(id),
                basketService.findBasketByProductId(id, silenceIds(user, uuid))
        );
    }

    @Operation(description = "Получить товары")
    @PostMapping("/all")
    public ProductAllResponse findAll(@RequestBody ProductFilter request,
                                      @ParameterObject @PageableDefault(size = 20) Pageable pageable,
                                      @AuthenticationPrincipal User user,
                                      @RequestHeader(value = "Guest-UUID", required = false) String uuid) {
        var products = productService.findAll(request, pageable);
        var baskets = basketService.findBasketsByProductIds(extractProductIds(products), silenceIds(user, uuid));
        return productMapper.fromPageProductToResponse(
                products, mapBasketsToMapProductCounts(baskets));
    }


}
