package org.tiradaus.infrastructure.persistence.jpa.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.tiradaus.domain.model.Event;
import org.tiradaus.domain.model.EventMode;
import org.tiradaus.infrastructure.persistence.jpa.entity.EventEntity;
import org.tiradaus.infrastructure.persistence.jpa.entity.GameEntity;

@Mapper(componentModel = "spring")
public interface EventJpaMapper {

    @Mapping(target = "gameId", source = "game.id")
    Event toDomain(EventEntity entity);

    @Mapping(target = "game.id", source = "gameId")
    EventEntity toEntity(Event domain);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "game", ignore = true)
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