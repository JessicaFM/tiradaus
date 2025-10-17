package org.tiradaus.domain.model;

import java.time.Instant;

public record User(
        Long id,
        String userName,
        String firstName,
        String lastName,
        String email,
        String password,   // hash
        boolean active,    // evita Boolean
        Long roleId,
        Instant lastLogin,
        Instant createdAt,
        Instant updatedAt
) {
    public User withPassword(String newHash) {
        return new User(id, userName, firstName, lastName, email, newHash, active, roleId, lastLogin, createdAt, updatedAt);
    }
    public User withId(Long newId) {
        return new User(newId, userName, firstName, lastName, email, password, active, roleId, lastLogin, createdAt, updatedAt);
    }
}