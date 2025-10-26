package org.tiradaus.domain.port.in;

public interface LogoutUserUseCase {

    record Command(String token) {}

    record Result(String message) {}

    Result logout(Command command);
}
