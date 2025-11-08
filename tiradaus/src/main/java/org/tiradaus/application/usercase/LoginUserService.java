package org.tiradaus.application.usercase;

import org.springframework.jdbc.core.JdbcTemplate;
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
    private final JdbcTemplate jdbcTemplate;

    public LoginUserService(LoadUserPort loadUserPort,
                            PasswordEncoder passwordEncoder,
                            JwtService jwtService,
                            JdbcTemplate jdbcTemplate) {
        this.loadUserPort = loadUserPort;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Result login(Command command) {
        System.out.println(command);
        if (command.username() == null || command.username().isBlank()) {
            throw new IllegalArgumentException("Username is required");
        }

        if (command.rawPassword() == null || command.rawPassword().isBlank()) {
            throw new IllegalArgumentException("Password is required");
        }

        User user = loadUserPort.findByUsername(command.username())
                .orElseThrow(() ->
                        new BadCredentialsException("Invalid username credentials"));

        if (!passwordEncoder.matches(command.rawPassword(), user.password())) {
            throw new BadCredentialsException("Invalid password credentials");
        }

        try {
            jdbcTemplate.update(
                    "update users set last_login = now(), updated_at = now() where id = ?",
                    user.id()
            );
        } catch (Exception e) {
            System.err.println("No pude actualizar last_login: " + e.getMessage());
        }

        String access = jwtService.generateAccessToken(user);
        String refresh = jwtService.generateRefreshToken(user);

        return new Result(access, refresh, user);
    }
}
