package org.tiradaus.infrastructure.web.dto;

import java.time.LocalDate;

public class UserResponse {
    private Long id;
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private boolean isActive;
    private LocalDate birthDate;

    private String eventName;
    private String gameTitle;

    public UserResponse(Long id,
                        String userName,
                        String firstName,
                        String lastName,
                        String email,
                        boolean isActive,
                        LocalDate birthDate) {
        this.id = id;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.isActive = isActive;
        this.birthDate = birthDate;
    }

    public Long getId() { return id; }
    public String getUserName() { return userName; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getEmail() { return email; }
    public LocalDate getBirthDate() { return birthDate; }

    public boolean isActive() { return isActive; }
    public boolean getIsActive() { return isActive; }

    public String getEventName() { return eventName; }
    public void setEventName(String eventName) { this.eventName = eventName; }

    public String getGameTitle() { return gameTitle; }
    public void setGameTitle(String gameTitle) { this.gameTitle = gameTitle; }
}