package org.tiradaus.infrastructure.web.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;

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

    @com.fasterxml.jackson.annotation.JsonProperty("role")
    @io.swagger.v3.oas.annotations.media.Schema(example = "2")
    Long roleId;

    @Past
    @Schema(
            type = "string",
            format = "date",
            example = "2010-05-21",
            description = "Date of Birth (YYYY-MM-DD)"
    )
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    LocalDate birthDate;

    public String getUsername() { return  username; }

    public String getEmail() { return email; }

    public String getPassword() { return password; }

    public String getFirstName() { return firstName; }

    public String getLastName() { return lastName; }

    public Long getRoleId() { return roleId; }

    public LocalDate getBirthDate() { return birthDate; }
}
