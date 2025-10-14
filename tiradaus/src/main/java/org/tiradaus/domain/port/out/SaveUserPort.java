package org.tiradaus.domain.port.out;

import org.tiradaus.domain.model.User;

public interface SaveUserPort {
    void save(User user);
}
