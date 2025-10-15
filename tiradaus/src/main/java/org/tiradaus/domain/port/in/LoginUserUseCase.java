package org.tiradaus.domain.port.in;

import org.tiradaus.domain.model.User;

public interface LoginUserUseCase {

    record Command(String username, String rawPassword) {}

    record Result(
            String accessToken,
            String refreshToken,
            User user
    ) {}

    Result login(Command command);
}
