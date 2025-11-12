package org.tiradaus.infrastructure.persistence.jpa.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.tiradaus.domain.model.Game;
import org.tiradaus.domain.model.GameType;
import org.tiradaus.domain.port.out.GameQueryPort;
import org.tiradaus.infrastructure.persistence.jpa.mapper.GameJpaMapper;
import org.tiradaus.infrastructure.persistence.jpa.repository.SpringDataGameRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GameQueryAdapter implements GameQueryPort {

    private final SpringDataGameRepository repo;
    private final GameJpaMapper jpaMapper;

    @Override
    public Page<Game> findAll(Pageable pageable) {
        return repo.findAll(pageable).map(jpaMapper::toDomain);
    }

    @Override
    public Page<Game> findByType(GameType type, Pageable pageable) {
        return repo.findByGameType(type, pageable).map(jpaMapper::toDomain);
    }

    @Override
    public List<Game> findAll() {
        return repo.findAll().stream().map(jpaMapper::toDomain).toList();
    }

    @Override
    public List<Game> findAllByType(GameType type) {
        return repo.findAllByGameType(type).stream().map(jpaMapper::toDomain).toList();
    }
}
