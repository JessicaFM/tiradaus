package org.tiradaus.domain.port.out;

import org.tiradaus.domain.model.User;
import java.util.Optional;

public interface LoadUserPort {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
}
