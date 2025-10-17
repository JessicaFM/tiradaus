package org.tiradaus.domain.port.in;

import org.tiradaus.infrastructure.web.dto.RegisterRequest;
import org.tiradaus.infrastructure.web.dto.RegisterResponse;

public interface RegisterUserUseCase {

    RegisterResponse register(RegisterRequest reg);
}
