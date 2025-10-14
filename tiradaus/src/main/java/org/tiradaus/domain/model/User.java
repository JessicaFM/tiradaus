package org.tiradaus.domain.model;

import java.time.Instant;

public class User {
    private final Long id;
    private final String userName;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String password;
    private final Boolean isActive;
    private final Role role;
    private final Instant lastLogin;
    private final Instant createdAt;
    private final Instant updatedAt;

    public User(Long id, String userName, String firstName, String lastName,
                String email, String password, boolean isActive, Role role,
                Instant lastLogin, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.isActive = isActive;
        this.role = role;
        this.lastLogin = lastLogin;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() { return id; }

    public String getUserName() { return userName; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public boolean getIsActive() { return isActive; }
    public Role getRole() { return role; }
    public Instant getLastLogin() { return lastLogin; }
    public Instant getCreatedAt() { return createdAt; }
    public Instant getUpdatedAt() { return updatedAt; }

}
