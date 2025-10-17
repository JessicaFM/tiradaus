package org.tiradaus.infrastructure.web.dto;

public record RegisterResponse(
        Long id,
        String username,
        String email,
        Long roleId,
        boolean isActive
) { }
