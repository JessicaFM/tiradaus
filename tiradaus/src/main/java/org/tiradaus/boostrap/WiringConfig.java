package org.tiradaus.boostrap;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.tiradaus.application.usercase.RegisterUserService;
import org.tiradaus.domain.port.in.RegisterUserUseCase;
import org.tiradaus.infrastructure.persistence.jpa.repository.SpringDataUserRepository;

@Configuration
public class WiringConfig {

    @Bean
    public RegisterUserUseCase registerUserUseCase(
            PasswordEncoder passwordEncoder,
            SpringDataUserRepository springDataUserRepository
    ) {
        return new RegisterUserService(springDataUserRepository, passwordEncoder);
    }
}
