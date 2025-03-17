package com.example.pioneerbackend.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationRequest extends AuthorisationRequest {
    @Schema(description = "User uuid")
    private String uuid;

}
