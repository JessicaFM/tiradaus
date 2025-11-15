package org.tiradaus.infrastructure.web.dto;

import org.tiradaus.domain.model.EventMode;

import java.sql.Timestamp;

public record EventResponse(
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
