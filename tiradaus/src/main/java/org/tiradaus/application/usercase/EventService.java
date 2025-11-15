package org.tiradaus.application.usercase;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import org.tiradaus.domain.model.Event;
import org.tiradaus.domain.model.EventMode;
import org.tiradaus.domain.port.in.EventUseCase;
import org.tiradaus.infrastructure.persistence.jpa.entity.EventEntity;
import org.tiradaus.infrastructure.persistence.jpa.mapper.EventJpaMapper;
import org.tiradaus.infrastructure.persistence.jpa.repository.SpringDataEventRepository;
import org.tiradaus.infrastructure.web.dto.EventPageResponse;
import org.tiradaus.infrastructure.web.dto.EventRequest;
import org.tiradaus.infrastructure.web.dto.EventResponse;
import org.tiradaus.infrastructure.web.mapper.EventDtoMapper;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EventService implements EventUseCase {

    private final SpringDataEventRepository repo;
    private final EventJpaMapper jpaMapper;
    private final EventDtoMapper dtoMapper;

    @Override
    @Transactional
    public EventResponse create(EventRequest req) {
        Event domain = dtoMapper.toDomain(req);
        EventEntity saved = repo.save(jpaMapper.toEntity(domain));
        return dtoMapper.toResponse(jpaMapper.toDomain(saved));
    }

    @Override
    @Transactional(readOnly = true)
    public EventResponse getById(Long id) {
        EventEntity e = repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return dtoMapper.toResponse(jpaMapper.toDomain(e));
    }

    @Override
    @Transactional
    public EventResponse update(Long id, EventRequest req) {
        EventEntity e = repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found"));
        Event patch = dtoMapper.toDomain(req);
        jpaMapper.updateEntity(e, patch);
        EventEntity saved = repo.save(e);
        return dtoMapper.toResponse(jpaMapper.toDomain(saved));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!repo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found");
        }
        repo.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public EventPageResponse<EventResponse> listPage(EventMode eventMode, Pageable pageable) {
        Page<EventEntity> page = (eventMode == null)
                ? repo.findAll(pageable)
                : repo.findByEventMode(eventMode, pageable);

        Page<EventResponse> mapper = page.map(e -> dtoMapper.toResponse(jpaMapper.toDomain(e)));
        return EventPageResponse.from(mapper);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EventResponse> listAll(EventMode eventMode) {
        List<EventEntity> entities = (eventMode == null)
                ? repo.findAll()
                : repo.findAllByEventMode(eventMode);

        return entities.stream()
                .map(e -> dtoMapper.toResponse(jpaMapper.toDomain(e)))
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<String> listModes() {
        return Arrays.stream(EventMode.values())
                .map(e -> e.name().toLowerCase(Locale.ROOT))
                .toList();
    }

    private Optional<EventMode> parseEventMode(EventMode eventMode) {
        return Optional.ofNullable(eventMode);
    }
}
