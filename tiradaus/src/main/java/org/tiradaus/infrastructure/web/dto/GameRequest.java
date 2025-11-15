package org.tiradaus.infrastructure.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.tiradaus.domain.model.GameType;

public class GameRequest {
    private String title;
    private String description;
    private GameType gameType;
    private String platform;
    private Integer minAge;

    @jakarta.validation.constraints.NotBlank
    @Size(max = 200)
    @io.swagger.v3.oas.annotations.media.Schema(example = "League of Legends")
    public String getTitle() {
        return title;
    }

    @io.swagger.v3.oas.annotations.media.Schema(example = "Long description of the video game")
    public String getDescription() {
        return description;
    }

    @NotNull
    @JsonProperty("gameType")
    @Schema(
            description = "Type pf game",
            example = "ONLINE",
            allowableValues = {"ONLINE", "PHYSICAL"}
    )
    public GameType getGameType() {
        return gameType;
    }

    @io.swagger.v3.oas.annotations.media.Schema(example = "XBOX")
    public String getPlatform() {
        return platform;
    }

    @Min(0)
    @Schema(example = "12", description = "Minim game to play the game")
    public Integer getMinAge() {
        return minAge;
    }
}
