package org.tiradaus.infrastructure.persistence.jpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tiradaus.domain.model.EventMode;
import org.tiradaus.infrastructure.persistence.jpa.entity.EventEntity;
import java.util.List;

@Repository
public interface SpringDataEventRepository extends JpaRepository<EventEntity, Long>  {
    Page<EventEntity> findByEventMode(EventMode eventMode, Pageable pageable);
    List<EventEntity> findAllByEventMode(EventMode eventMode);
}
