package org.tiradaus.infrastructure.persistence.jpa.mapper;

import org.mapstruct.*;
import org.tiradaus.domain.model.Game;
import org.tiradaus.domain.model.GameType;
import org.tiradaus.infrastructure.persistence.jpa.entity.GameEntity;

@Mapper(componentModel = "spring")
public interface GameJpaMapper {

    GameEntity toEntity(Game domain);

    Game toDomain(GameEntity entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(@MappingTarget GameEntity target, Game source);

    default String map(GameType type) {
        if (type == null) return null;
        return type.name().toLowerCase();
    }

    default GameType map(String value) {
        if (value == null) return null;
        return GameType.valueOf(value.toUpperCase());
    }

    @AfterMapping
    default void touchUpdatedAt(@MappingTarget GameEntity target) {
        if (target.getId() != null) target.setUpdatedAt(java.time.Instant.now());
    }
}
