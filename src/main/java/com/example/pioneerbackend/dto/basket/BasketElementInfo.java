package com.example.pioneerbackend.dto.basket;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BasketElementInfo {
    private Long id;
    private Long productId;
    private Integer count;
    private Double price;
    private String title;
    private Long imageId;
}
