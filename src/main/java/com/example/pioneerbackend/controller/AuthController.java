package com.example.pioneerbackend.controller;

import com.example.pioneerbackend.dto.auth.AuthenticationRequest;
import com.example.pioneerbackend.dto.auth.AuthResponse;
import com.example.pioneerbackend.dto.auth.AuthorisationRequest;
import com.example.pioneerbackend.mapper.AuthMapper;
import com.example.pioneerbackend.service.auth.AuthenticationService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService authenticationService;
    private final AuthMapper mapper;

    @PostMapping("/register")
    public AuthResponse authenticate(@RequestBody AuthenticationRequest request) {
        return authenticationService.authenticate(mapper.toEntity(request));
    }

    @PostMapping("/login")
    public AuthResponse authorize(@RequestBody AuthorisationRequest request) {
        return authenticationService.authorize(mapper.toEntity(request));
    }

}
