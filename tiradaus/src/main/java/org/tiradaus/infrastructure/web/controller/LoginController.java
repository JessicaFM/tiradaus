package org.tiradaus.infrastructure.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tiradaus.domain.port.in.LoginUserUseCase;
import org.tiradaus.infrastructure.web.dto.LoginResponse;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Login", description = "Login & tokens")
public class LoginController {

    public record LoginRequest(String username, String password) {}
    public record TokenResponse(String accessToke, String refreshToken) {}
    private final LoginUserUseCase loginUserUseCase;

    public LoginController(LoginUserUseCase loginUserUseCase) {
        this.loginUserUseCase = loginUserUseCase;
    }

    @PostMapping("/login")
    @Operation(summary = "Login with username & password")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest req) {
        var res = loginUserUseCase.login(
                new LoginUserUseCase.Command(
                        req.username(),
                        req.password()
                )
        );

        return ResponseEntity
                .ok(new LoginResponse(
                        res.accessToken(),
                        res.refreshToken(),
                        res.user().roleId(),
                        res.user().userName())
                );
    }

}
