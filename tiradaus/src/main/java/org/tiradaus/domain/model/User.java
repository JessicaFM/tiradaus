package org.tiradaus.domain.model;

import java.time.Instant;
import java.util.Date;

public record User(
        Long id,
        String userName,
        String firstName,
        String lastName,
        String email,
        String password,   // hash
        boolean active,    // evita Boolean
        Long roleId,
        Date birthDate,
        Instant lastLogin,
        Instant createdAt,
        Instant updatedAt
) {
    public User withPassword(String newHash) {
        return new User(id, userName, firstName, lastName, email, newHash, active, roleId, birthDate, lastLogin, createdAt, updatedAt);
    }
    public User withId(Long newId) {
        return new User(newId, userName, firstName, lastName, email, password, active, roleId, birthDate, lastLogin, createdAt, updatedAt);
    }
}