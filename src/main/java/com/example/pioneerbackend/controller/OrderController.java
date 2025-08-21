package com.example.pioneerbackend.controller;

import com.example.pioneerbackend.dto.order.OrderRequest;
import com.example.pioneerbackend.entity.user.User;
import com.example.pioneerbackend.service.order.OrderService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import static com.example.pioneerbackend.util.UserUuidUtils.silenceIds;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService service;

    @PostMapping
    public void create(@RequestBody OrderRequest request,
                       @AuthenticationPrincipal User user,
                       @RequestHeader(value = "Guest-UUID", required = false) String uuid) throws MessagingException {
        service.createOrder(request, silenceIds(user, uuid));
    }

}
