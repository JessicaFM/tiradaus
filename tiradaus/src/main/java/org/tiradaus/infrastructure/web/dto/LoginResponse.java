package org.tiradaus.infrastructure.web.dto;

public record LoginResponse(
        @io.swagger.v3.oas.annotations.media.Schema(description = "JWT access token")
        String accessToken,

        @io.swagger.v3.oas.annotations.media.Schema(description = "JWT refresh token")
        String refreshToken,

        @io.swagger.v3.oas.annotations.media.Schema(example = "ADMIN")
        String role,

        @io.swagger.v3.oas.annotations.media.Schema(example = "admin")
        String username
) {}