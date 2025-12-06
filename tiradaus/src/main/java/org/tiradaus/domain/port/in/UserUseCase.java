package org.tiradaus.domain.port.in;

import org.tiradaus.infrastructure.web.dto.UserRequest;
import org.tiradaus.infrastructure.web.dto.UserResponse;

import java.util.List;

public interface UserUseCase {
    UserResponse getById(Long id);

    List<UserResponse> getAll();

    UserResponse update(Long id, UserRequest request);
}
