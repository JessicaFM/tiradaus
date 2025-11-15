package org.tiradaus.infrastructure.web.mapper;

import org.mapstruct.*;
import org.tiradaus.domain.model.Event;
import org.tiradaus.domain.model.EventMode;
import org.tiradaus.infrastructure.web.dto.EventRequest;
import org.tiradaus.infrastructure.web.dto.EventResponse;

@Mapper(componentModel = "spring")
public interface EventDtoMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true)
    })
    Event toDomain(EventRequest request);

    EventResponse toResponse(Event domain);

    @BeanMapping(nullValuePropertyMappingStrategy =  NullValuePropertyMappingStrategy.IGNORE)
    void updateDomain(@MappingTarget Event target, EventRequest request);

    default EventMode map(String s) {
        if (s == null) return null;
        return EventMode.valueOf(s.trim().toUpperCase());
    }

    default String map(EventMode em) {
        if (em == null) return null;
        return em.name().toLowerCase();
    }
}
