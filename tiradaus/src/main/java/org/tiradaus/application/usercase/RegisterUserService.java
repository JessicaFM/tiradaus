package org.tiradaus.application.usercase;

import org.tiradaus.domain.model.Role;
import org.tiradaus.domain.model.User;
import org.tiradaus.domain.port.in.RegisterUserUseCase;
import org.tiradaus.domain.port.out.LoadUserPort;
import org.tiradaus.domain.port.out.SaveUserPort;
import org.springframework.security.crypto.password.PasswordEncoder;

public class RegisterUserService implements RegisterUserUseCase {

    private final LoadUserPort loadUserPort;
    private final SaveUserPort saveUserPort;
    private final PasswordEncoder passwordEncoder;

    public RegisterUserService(LoadUserPort loadUserPort, SaveUserPort saveUserPort, PasswordEncoder passwordEncoder) {
        this.loadUserPort = loadUserPort;
        this.saveUserPort = saveUserPort;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void register(Command command) {
        if(command.email() == null || command.email().isBlank()) {
            throw  new IllegalArgumentException("Email is required");
        }

        if(command.username() == null || command.username().isBlank()) {
            throw new IllegalArgumentException("Username is required");
        }

        if(command.rawPassword() == null || command.rawPassword().isBlank()) {
            throw new IllegalArgumentException("Password is required");
        }

        loadUserPort.findByUsername(command.username())
                .ifPresent(u -> { throw new IllegalArgumentException("Username already exists"); });

        String hash = passwordEncoder.encode(command.rawPassword());

        User user = new User(
                null,
                command.username(),
                "",
                "",
                command.email(),
                hash,
                true,
                Role.USER,
                null,
                null,
                null
        );

        saveUserPort.save(user);
    }
}
