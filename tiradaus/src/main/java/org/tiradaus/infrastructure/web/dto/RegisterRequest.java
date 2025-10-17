package org.tiradaus.infrastructure.web.dto;

public class RegisterRequest {
    @jakarta.validation.constraints.NotBlank
    @io.swagger.v3.oas.annotations.media.Schema(example = "user")
    private String username;

    @jakarta.validation.constraints.Email
    @jakarta.validation.constraints.NotBlank
    @io.swagger.v3.oas.annotations.media.Schema(example = "user@email.com")
    private String email;

    @jakarta.validation.constraints.NotBlank
    @com.fasterxml.jackson.annotation.JsonProperty("password")
    @io.swagger.v3.oas.annotations.media.Schema(example = "user1234")
    private String password;

    @jakarta.validation.constraints.Size(max = 100)
    String firstName;

    @jakarta.validation.constraints.Size(max = 100)
    String lastName;

    Long roleId;

    public String getUsername() { return  username; }

    public String getEmail() { return email; }

    public String getPassword() { return password; }

    public String getFirstName() { return firstName; }

    public String getLastName() { return lastName; }

    public Long getRoleId() { return roleId; }
}
