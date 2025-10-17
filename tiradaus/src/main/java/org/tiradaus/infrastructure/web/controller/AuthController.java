package org.tiradaus.infrastructure.web.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tiradaus.domain.port.in.RegisterUserUseCase;
import org.tiradaus.infrastructure.web.dto.RegisterRequest;
import org.tiradaus.infrastructure.web.dto.RegisterResponse;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final RegisterUserUseCase registerUserUseCase;

    public AuthController(RegisterUserUseCase registerUserUseCase) {
        this.registerUserUseCase = registerUserUseCase;
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@Valid @RequestBody RegisterRequest req) {
        RegisterResponse response = registerUserUseCase.register(req);

        return ResponseEntity.ok(response);
    }
}
