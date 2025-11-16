package org.tiradaus.infrastructure.web.mapper;

import org.mapstruct.*;
import org.tiradaus.domain.model.Event;
import org.tiradaus.domain.model.EventMode;
import org.tiradaus.infrastructure.persistence.jpa.entity.EventEntity;
import org.tiradaus.infrastructure.persistence.jpa.entity.GameEntity;
import org.tiradaus.infrastructure.web.dto.EventRequest;
import org.tiradaus.infrastructure.web.dto.EventResponse;

@Mapper(componentModel = "spring")
public interface EventDtoMapper {

    Event toDomain(EventRequest request);

    EventEntity toEntity(Event domain);

    EventResponse toResponse(Event domain);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(@MappingTarget EventEntity target, Event source);

    default Long map(GameEntity game) {
        return game != null ? game.getId() : null;
    }

    default GameEntity map(Long id) {
        if (id == null) return null;
        GameEntity game = new GameEntity();
        game.setId(id);
        return game;
    }

    default String map(EventMode eventMode) {
        if (eventMode == null) return null;
        return eventMode.name().toLowerCase();
    }

    default EventMode map(String value) {
        if (value == null) return null;
        return EventMode.valueOf(value.toUpperCase());
    }
}
