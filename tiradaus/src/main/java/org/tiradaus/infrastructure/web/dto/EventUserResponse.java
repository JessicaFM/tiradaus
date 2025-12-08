package org.tiradaus.infrastructure.web.dto;

import org.tiradaus.domain.model.EventMode;

import java.sql.Timestamp;
import java.util.List;

public class EventUserResponse {
    private Long eventId;
    private String eventName;
    private String eventDescription;
    private Timestamp startDate;
    private Timestamp endDate;
    private EventMode eventMode;
    private String eventLocation;
    private Integer eventPlayers;

    private Long gameId;
    private String gameTitle;
    private String gameImageUrl;

    private List<UserInEvent> users;

    public EventUserResponse(Long eventId,
                             String eventName,
                             String eventDescription,
                             Timestamp startDate,
                             Timestamp endDate,
                             EventMode eventMode,
                             String eventLocation,
                             Integer eventPlayers,
                             Long gameId,
                             String gameTitle,
                             String gameImageUrl,
                             List<UserInEvent> users) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.eventDescription = eventDescription;
        this.startDate = startDate;
        this.endDate = endDate;
        this.eventMode = eventMode;
        this.eventLocation = eventLocation;
        this.eventPlayers = eventPlayers;
        this.gameId = gameId;
        this.gameTitle = gameTitle;
        this.gameImageUrl = gameImageUrl;
        this.users = users;
    }

    public Long getEventId() { return eventId; }
    public String getEventName() { return eventName; }
    public String getEventDescription() { return eventDescription; }
    public Timestamp getStartDate() { return startDate; }
    public Timestamp getEndDate() { return endDate; }
    public EventMode getEventMode() { return eventMode; }
    public String getEventLocation() { return eventLocation; }
    public Integer getEventPlayers() { return eventPlayers; }
    public Long getGameId() { return gameId; }
    public String getGameTitle() { return gameTitle; }
    public String getGameImageUrl() { return gameImageUrl; }
    public List<UserInEvent> getUsers() { return users; }

    public static class UserInEvent {
        private Long userId;
        private String userName;
        private String firstName;

        public UserInEvent(Long userId,
                           String userName,
                           String firstName) {
            this.userId = userId;
            this.userName = userName;
            this.firstName = firstName;
        }

        public Long getUserId() { return userId; }
        public String getUserName() { return userName; }
        public String getFirstName() { return firstName; }
    }
}
