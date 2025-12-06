package org.tiradaus.infrastructure.web.controller;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.tiradaus.domain.port.in.UserUseCase;
import org.tiradaus.infrastructure.web.dto.UserRequest;
import org.tiradaus.infrastructure.web.dto.UserResponse;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@Tag(
        name = "Users",
        description = "Users actions with JWT token issuance",
        externalDocs = @ExternalDocumentation(
                description = "Users flow details",
                url = "https://github.com/JessicaFM/tiradaus"
        )
)
public class UserController {

    private final UserUseCase userUseCase;

    public UserController(UserUseCase userUseCase) {
        this.userUseCase = userUseCase;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable Long id) {
        return ResponseEntity.ok(userUseCase.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return ResponseEntity.ok(userUseCase.getAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(
            @PathVariable Long id,
            @RequestBody UserRequest request
    ) {
        return ResponseEntity.ok(userUseCase.update(id, request));
    }
}