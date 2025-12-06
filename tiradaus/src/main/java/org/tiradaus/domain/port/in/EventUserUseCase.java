package org.tiradaus.domain.port.in;

import org.tiradaus.infrastructure.web.dto.EventUserResponse;
import org.tiradaus.infrastructure.web.dto.UserResponse;

import java.util.List;

public interface EventUserUseCase {
    void addUserToEvent(Long eventId, Long userId);

    EventUserResponse getUsersByEvent(Long eventId);
}
