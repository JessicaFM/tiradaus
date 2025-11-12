package org.tiradaus.infrastructure.web.dto;

import org.tiradaus.domain.model.GameType;

public record GameResponse(
    Long id,
    String title,
    String description,
    GameType gameType,
    String platform,
    Integer minAge
) { }
