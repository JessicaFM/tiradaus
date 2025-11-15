package org.tiradaus.infrastructure.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.tiradaus.domain.model.EventMode;

import java.sql.Time;
import java.sql.Timestamp;

public class EventRequest {
    private String name;
    private String description;
    private Timestamp startDate;
    private Timestamp endDate;
    private EventMode eventMode;
    private Long gameId;
    private String location;
    private Integer players;

    @jakarta.validation.constraints.NotBlank
    @Size(max = 200)
    @io.swagger.v3.oas.annotations.media.Schema(example = "Torneig classificatori de League of Legends")
    public String getName() {
        return name;
    }

    @io.swagger.v3.oas.annotations.media.Schema(example = "Torneig 5v5 en línia amb fase de grups i eliminatòries. Obert a equips amateurs.")
    public String getDescription() {
        return description;
    }

    @jakarta.validation.constraints.NotNull
    @io.swagger.v3.oas.annotations.media.Schema(example = "2025-02-01 18:00:00+01")
    public Timestamp getStartDate() {
        return startDate;
    }

    @io.swagger.v3.oas.annotations.media.Schema(example = "2025-02-01 21:00:00+01")
    public Timestamp getEndDate() {
        return endDate;
    }

    @NotNull
    @JsonProperty("eventMode")
    @Schema(
            description = "Mode of game",
            example = "REAL_LIFE",
            allowableValues = {"REAL_LIFE", "ONLINE"}
    )
    public EventMode getEventMode() {
        return eventMode;
    }

    @com.fasterxml.jackson.annotation.JsonProperty("game")
    @io.swagger.v3.oas.annotations.media.Schema(example = "1")
    public Long getGameId() {
        return gameId;
    }

    @io.swagger.v3.oas.annotations.media.Schema(example = "Sale servidor 5")
    public String getLocation() {
        return location;
    }

    public Integer getPlayers() {
        return players;
    }
}

