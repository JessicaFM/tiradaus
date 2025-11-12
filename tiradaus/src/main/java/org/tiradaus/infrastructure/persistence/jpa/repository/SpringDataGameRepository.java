package org.tiradaus.infrastructure.persistence.jpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tiradaus.domain.model.GameType;
import org.tiradaus.infrastructure.persistence.jpa.entity.GameEntity;
import java.util.List;

@Repository
public interface SpringDataGameRepository extends JpaRepository<GameEntity, Long> {
    Page<GameEntity> findByGameType(GameType type, Pageable pageable);
    List<GameEntity> findAllByGameType(GameType type);
}
