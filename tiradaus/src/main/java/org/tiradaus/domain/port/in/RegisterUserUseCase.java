package org.tiradaus.domain.port.in;

public interface RegisterUserUseCase {

    record Command(String username, String email, String rawPassword) {}

    void register(Command command);
}
