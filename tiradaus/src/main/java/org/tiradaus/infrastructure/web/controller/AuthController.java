package org.tiradaus.infrastructure.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tiradaus.domain.port.in.RegisterUserUseCase;
import org.tiradaus.infrastructure.web.dto.RegisterRequest;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final RegisterUserUseCase registerUserUseCase;

    public AuthController(RegisterUserUseCase registerUserUseCase) {
        this.registerUserUseCase = registerUserUseCase;
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody RegisterRequest req) {
        registerUserUseCase.register(new RegisterUserUseCase.Command(
            req.getUsername(),
            req.getEmail(),
            req.getPassword()
        ));

        return ResponseEntity.ok().build();
    }
}
