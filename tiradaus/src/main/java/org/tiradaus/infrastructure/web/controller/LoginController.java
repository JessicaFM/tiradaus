package org.tiradaus.infrastructure.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tiradaus.domain.port.in.LoginUserUseCase;
import org.tiradaus.domain.port.in.LogoutUserUseCase;
import org.tiradaus.infrastructure.web.dto.LoginResponse;
import org.tiradaus.infrastructure.web.dto.LogoutResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@Tag(
        name = "Login & Logout",
        description = "User authentication and JWT token issuance",
        externalDocs = @ExternalDocumentation(
                description = "Authentication flow details",
                url = "https://github.com/JessicaFM/tiradaus"
        )
)
public class LoginController {
    @Schema(name = "LoginRequest", description = "User credentials")
    public record LoginRequest(
            @Schema(description = "Username", example = "jdoe")
            @NotBlank(message = "username is required")
            String username,

            @Schema(description = "User password", example = "P@ssw0rd!")
            @NotBlank(message = "password is required")
            String password
    ) {}

    public record TokenResponse(String accessToke, String refreshToken) {}
    private final LoginUserUseCase loginUserUseCase;
    private final LogoutUserUseCase logoutUserUseCase;

    public LoginController(
            LoginUserUseCase loginUserUseCase,
            LogoutUserUseCase logoutUserUseCase
    ) {
        this.loginUserUseCase = loginUserUseCase;
        this.logoutUserUseCase = logoutUserUseCase;
    }

    @PostMapping(
            path = "/login",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(
            summary = "Login with username and password",
            description = """
            Validates user credentials and returns a JWT access token plus a refresh token.
            Use the access token in the Authorization header as: Bearer &lt;token&gt;.
            """
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Authenticated. Returns tokens and minimal user info.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = LoginResponse.class),
                            examples = @ExampleObject(
                                    name = "Success response",
                                    value = """
                        {
                          "accessToken": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
                          "refreshToken": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
                          "roleId": "ADMIN",
                          "userName": "jdoe"
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
                                    description = "Validation error format",
                                    example = """
                        {
                          "error": "BAD_REQUEST",
                          "message": "Validation failed",
                          "details": {
                            "username": "username is required",
                            "password": "password is required"
                          }
                        }
                        """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Invalid credentials",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(
                                    name = "UnauthorizedError",
                                    example = """
                        {
                          "error": "UNAUTHORIZED",
                          "message": "Invalid username or password"
                        }
                        """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "429",
                    description = "Too many login attempts (rate limited)",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(
                                    name = "TooManyRequestsError",
                                    example = """
                        {
                          "error": "TOO_MANY_REQUESTS",
                          "message": "Try again in 60 seconds"
                        }
                        """
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Unexpected server error"
            )
    })
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest req) {
        System.out.println("username=" + req.username() + ", pw_len=" + (req.password()==null?null:req.password().length()));

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

    @PostMapping(
            path = "/logout",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(
            summary = "Logout (invalidate current JWT)",
            description = """
        Invalidates the provided Bearer token.
        Send the Authorization header as: Bearer <token>.
        """
    )
    public ResponseEntity<LogoutResponse> logout(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String authHeader) {

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            // devuelve 401 en vez de tirar IllegalArgumentException
            return ResponseEntity
                    .status(401)
                    .body(new LogoutResponse("Missing or invalid Authorization header (Bearer token required)"));
        }

        String token = authHeader.substring(7);
        var result = logoutUserUseCase.logout(new LogoutUserUseCase.Command(token));
        return ResponseEntity.ok(new LogoutResponse(result.message()));
    }

}
