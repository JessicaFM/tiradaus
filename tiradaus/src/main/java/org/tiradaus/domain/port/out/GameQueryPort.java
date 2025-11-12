package org.tiradaus.domain.port.out;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.tiradaus.domain.model.Game;
import org.tiradaus.domain.model.GameType;

import java.util.List;

public interface GameQueryPort {
    Page<Game> findAll(Pageable pageable);
    Page<Game> findByType(GameType type, Pageable pageable);
    List<Game> findAll();
    List<Game> findAllByType(GameType type);
}
