package com.example.pioneerbackend.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthorisationRequest {
    @Schema(description = "User email")
    private String email;
    @Schema(description = "User password")
    private String password;

}
