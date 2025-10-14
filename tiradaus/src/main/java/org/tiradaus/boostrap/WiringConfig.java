package org.tiradaus.boostrap;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.tiradaus.application.usercase.RegisterUserService;
import org.tiradaus.domain.port.in.RegisterUserUseCase;
import org.tiradaus.domain.port.out.LoadUserPort;
import org.tiradaus.domain.port.out.SaveUserPort;

@Configuration
public class WiringConfig {

    @Bean
    public RegisterUserUseCase registerUserUseCase(
            LoadUserPort loadUserPort,
            SaveUserPort saveUserPort,
            PasswordEncoder passwordEncoder
    ) {
        return new RegisterUserService(loadUserPort, saveUserPort, passwordEncoder);
    }
}
