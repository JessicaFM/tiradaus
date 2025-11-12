package org.tiradaus.infrastructure.web.mapper;

import org.mapstruct.*;
import org.tiradaus.domain.model.Game;
import org.tiradaus.domain.model.GameType;
import org.tiradaus.infrastructure.web.dto.GameRequest;
import org.tiradaus.infrastructure.web.dto.GameResponse;

@Mapper(componentModel = "spring")
public interface GameDtoMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true),
    })
    Game toDomain(GameRequest request);

    GameResponse toResponse(Game domain);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateDomain(@MappingTarget Game target, GameRequest request);

    default GameType map(String s) {
        if (s == null) return null;
        return GameType.valueOf(s.trim().toUpperCase());
    }
    default String map(GameType gt) {
        if (gt == null) return null;
        return gt.name().toLowerCase();
    }
}