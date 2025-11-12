package org.tiradaus.domain.model;

import java.time.Instant;

public record Game(
    Long id,
    String title,
    String description,
    GameType gameType,
    String platform,
    Integer minAge,
    Instant createdAt,
    Instant updatedAt
    ) {
}
