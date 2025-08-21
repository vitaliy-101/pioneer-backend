package com.example.pioneerbackend.dto.order;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Запрос на создание заказа пользователем")
public class OrderRequest {
    private String name;
    private String phoneNumber;
    private String mail;
}
