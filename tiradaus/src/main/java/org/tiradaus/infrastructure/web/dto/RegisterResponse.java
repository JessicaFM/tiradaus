package org.tiradaus.infrastructure.web.dto;

import java.time.LocalDate;

public record RegisterResponse(
        Long id,
        String username,
        String email,
        Long roleId,
        boolean isActive,
        LocalDate birthDate
) { }
