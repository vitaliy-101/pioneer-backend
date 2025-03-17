package com.example.pioneerbackend.controller;

import com.example.pioneerbackend.dto.basket.BasketCountUpdateResponse;
import com.example.pioneerbackend.dto.basket.BasketCreateRequest;
import com.example.pioneerbackend.dto.basket.BasketGetResponse;
import com.example.pioneerbackend.entity.user.User;
import com.example.pioneerbackend.service.basket.BasketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import static com.example.pioneerbackend.util.UserUuidUtils.silenceIds;

@RestController
@RequestMapping("api/v1/basket")
@RequiredArgsConstructor
public class BasketController {
    private final BasketService service;

    @PostMapping
    public ResponseEntity<String> create(
            @AuthenticationPrincipal User user,
            @RequestHeader(value = "Guest-UUID", required = false) String uuid,
            @RequestBody BasketCreateRequest request) {
        service.create(user, uuid, request);
        return ResponseEntity.ok("Product added to basket");
    }

    @GetMapping
    public BasketGetResponse findAll(@AuthenticationPrincipal User user,
                                     @RequestHeader(value = "Guest-UUID", required = false) String uuid) {
        return service.findAll(silenceIds(user, uuid));
    }

    @PutMapping("/count/{id}")
    public BasketCountUpdateResponse updateCount(@AuthenticationPrincipal User user,
                                                 @RequestHeader(value = "Guest-UUID", required = false) String uuid,
                                                 @PathVariable("id") Long productId,
                                                 @RequestParam(name = "count") Integer count) {
        service.updateCount(count, productId, silenceIds(user, uuid));
        return new BasketCountUpdateResponse(count);
    }

    @DeleteMapping("/{id}")
    public void deleteBasketProduct(@AuthenticationPrincipal User user,
                                    @RequestHeader(value = "Guest-UUID", required = false) String uuid,
                                    @PathVariable("id") Long productId) {
        service.deleteById(productId, silenceIds(user, uuid));
    }




}
