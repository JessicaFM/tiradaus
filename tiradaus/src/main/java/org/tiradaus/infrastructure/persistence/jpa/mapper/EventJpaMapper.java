package org.tiradaus.infrastructure.persistence.jpa.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.tiradaus.domain.model.Event;
import org.tiradaus.domain.model.EventMode;
import org.tiradaus.infrastructure.persistence.jpa.entity.EventEntity;

@Mapper(componentModel = "sprint")
public interface EventJpaMapper {

    EventEntity toEntity(Event domain);

    Event toDomain(EventEntity entity);

    @BeanMapping(nullValuePropertyMappingStrategy =  NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(@MappingTarget EventEntity target, Event source);

    default String map(EventMode eventMode) {
        if (eventMode == null) return null;
        return eventMode.name().toLowerCase();
    }

    default EventMode map(String value) {
        if (value == null) return null;
        return EventMode.valueOf(value.toUpperCase());
    }
}
