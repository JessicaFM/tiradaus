package org.tiradaus.infrastructure.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tiradaus.domain.port.in.RegisterUserUseCase;
import org.tiradaus.infrastructure.web.dto.RegisterRequest;
import org.tiradaus.infrastructure.web.dto.RegisterResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@Tag(
        name = "Registration",
        description = "User registration",
        externalDocs = @ExternalDocumentation(
                description = "Account lifecycle",
                url = "https://github.com/JessicaFM/tiradaus"
        )
)
public class AuthController {

    private final RegisterUserUseCase registerUserUseCase;

    public AuthController(RegisterUserUseCase registerUserUseCase) {
        this.registerUserUseCase = registerUserUseCase;
    }

    @PostMapping(
            path = "/register",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(
            summary = "Register a new user",
            description = """
            Creates a new user account. Depending on configuration, the account may require \
            email verification before the first login. The password must comply with the security policy.
            """
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "User created",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = RegisterResponse.class),
                            examples = @ExampleObject(
                                    name = "Created response",
                                    value = """
                        {
                          "userId": 123,
                          "username": "jdoe",
                          "email": "jdoe@example.com",
                          "roleId": 2,
                          "active": true
                        }
                        """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Validation error (missing or malformed fields)",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(
                                    name = "ValidationError",
                                    example = """
                        {
                          "error": "BAD_REQUEST",
                          "message": "Validation failed",
                          "details": {
                            "email": "must be a well-formed email address",
                            "password": "must be at least 8 characters"
                          }
                        }
                        """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Username or email already exists",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(
                                    name = "ConflictError",
                                    example = """
                        {
                          "error": "CONFLICT",
                          "message": "Username already exists"
                        }
                        """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "422",
                    description = "Policy/semantic violation (e.g., invalid role for self-registration)",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(
                                    name = "UnprocessableEntity",
                                    example = """
                        {
                          "error": "UNPROCESSABLE_ENTITY",
                          "message": "Invalid role"
                        }
                        """
                            )
                    )
            ),
            @ApiResponse(responseCode = "500", description = "Unexpected server error")
    })
    public ResponseEntity<RegisterResponse> register(@Valid @RequestBody RegisterRequest req) {
        RegisterResponse response = registerUserUseCase.register(req);

        System.out.println(req.getUsername());
        System.out.println(req.getEmail());

        return ResponseEntity.ok(response);
    }
}
