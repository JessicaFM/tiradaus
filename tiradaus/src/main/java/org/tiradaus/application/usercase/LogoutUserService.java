package org.tiradaus.application.usercase;

import org.springframework.stereotype.Service;
import org.tiradaus.domain.port.in.LogoutUserUseCase;
import org.tiradaus.security.JwtService;

@Service
public class LogoutUserService implements LogoutUserUseCase {

    private final JwtService jwtService;

    public LogoutUserService(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    public Result logout(Command command) {
        String token = command.token();

        if (token == null || token.isBlank()) {
            throw new IllegalArgumentException("Missing or invalid Authorization header (Bearer token required)");
        }

        jwtService.blacklistToken(token);

        return new Result("Has tancat la sessió correctament.");
    }
}

