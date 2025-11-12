package org.tiradaus.domain.port.in;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.tiradaus.domain.model.GameType;
import org.tiradaus.infrastructure.web.dto.GameRequest;
import org.tiradaus.infrastructure.web.dto.GameResponse;
import org.tiradaus.infrastructure.web.dto.PageResponse;

import java.util.List;

public interface GameUseCase {

    GameResponse create(GameRequest req);

    GameResponse getById(Long id);

    GameResponse update(Long id, GameRequest req);

    void delete(Long id);

    PageResponse<GameResponse> listPage(GameType gameType, Pageable pageable);

    List<GameResponse> listAll(GameType gameType);

    List<String> listTypes();
}
