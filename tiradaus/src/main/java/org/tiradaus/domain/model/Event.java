package org.tiradaus.domain.model;

import java.sql.Timestamp;

public record Event(
        Long id,
        String name,
        String description,
        Timestamp startDate,
        Timestamp endDate,
        EventMode eventMode,
        Long gameId,
        String location,
        Integer players
) {}
