package org.tiradaus.application.usercase;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import org.tiradaus.domain.model.Game;
import org.tiradaus.domain.model.GameType;
import org.tiradaus.domain.port.in.GameUseCase;
import org.tiradaus.infrastructure.persistence.jpa.entity.GameEntity;
import org.tiradaus.infrastructure.persistence.jpa.mapper.GameJpaMapper;
import org.tiradaus.infrastructure.persistence.jpa.repository.SpringDataGameRepository;
import org.tiradaus.infrastructure.web.dto.GameRequest;
import org.tiradaus.infrastructure.web.dto.GameResponse;
import org.tiradaus.infrastructure.web.dto.PageResponse;
import org.tiradaus.infrastructure.web.mapper.GameDtoMapper;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GameService implements GameUseCase {

    private final SpringDataGameRepository repo;
    private final GameJpaMapper jpaMapper;
    private final GameDtoMapper dtoMapper;

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    public GameResponse create(GameRequest req) {
        Game domain = dtoMapper.toDomain(req);
        GameEntity saved = repo.save(jpaMapper.toEntity(domain));
        return dtoMapper.toResponse(jpaMapper.toDomain(saved));
    }

    @Override
    @Transactional(readOnly = true)
    public GameResponse getById(Long id) {
        GameEntity e = repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Game not found"));
        return dtoMapper.toResponse(jpaMapper.toDomain(e));
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    public GameResponse update(Long id, GameRequest req) {
        GameEntity e = repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Game not found"));
        Game patch = dtoMapper.toDomain(req);
        jpaMapper.updateEntity(e, patch); // MapStruct IGNORE nulls
        GameEntity saved = repo.save(e);
        return dtoMapper.toResponse(jpaMapper.toDomain(saved));
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    public void delete(Long id) {
        if (!repo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Game not found");
        }
        repo.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public PageResponse<GameResponse> listPage(GameType type, Pageable pageable) {
        Page<GameEntity> page = (type == null)
                ? repo.findAll(pageable)
                : repo.findByGameType(type, pageable);

        Page<GameResponse> mapped = page.map(e -> dtoMapper.toResponse(jpaMapper.toDomain(e)));
        return PageResponse.from(mapped);
    }

    @Override
    @Transactional(readOnly = true)
    public List<GameResponse> listAll(GameType type) {
        List<GameEntity> entities = (type == null)
                ? repo.findAll()
                : repo.findAllByGameType(type);

        return entities.stream()
                .map(e -> dtoMapper.toResponse(jpaMapper.toDomain(e)))
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<String> listTypes() {
        return Arrays.stream(GameType.values())
                .map(t -> t.name().toLowerCase(Locale.ROOT))
                .toList();
    }

    private Optional<GameType> parseGameType(GameType type) {
        return Optional.ofNullable(type);
    }
}
