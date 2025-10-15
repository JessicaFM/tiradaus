package org.tiradaus.application.usercase;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.tiradaus.domain.model.User;
import org.tiradaus.domain.port.in.LoginUserUseCase;
import org.tiradaus.domain.port.out.LoadUserPort;
import org.tiradaus.security.JwtService;

@Service
public class LoginUserService implements LoginUserUseCase {

    private final LoadUserPort loadUserPort;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public LoginUserService(LoadUserPort loadUserPort,
                            PasswordEncoder passwordEncoder,
                            JwtService jwtService) {
        this.loadUserPort = loadUserPort;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @Override
    public Result login(Command command) {
        if (command.username() == null || command.username().isBlank()) {
            throw new IllegalArgumentException("Username is required");
        }

        if (command.rawPassword() == null || command.rawPassword().isBlank()) {
            throw new IllegalArgumentException("Password is required");
        }

        User user = loadUserPort.findByUsername(command.username())
                .orElseThrow(() ->
                        new BadCredentialsException("Invalid username credentials"));

        System.out.println("command: " + command.rawPassword());
        System.out.println("user: " + user.getPassword());

        if (!passwordEncoder.matches(command.rawPassword(), user.getPassword())) {
            throw new BadCredentialsException("Invalid password credentials");
        }

        String access = jwtService.generateAccessToken(user);
        String refresh = jwtService.generateRefreshToken(user);

        return new Result(access, refresh, user);
    }
}
