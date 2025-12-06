package org.tiradaus.infrastructure.persistence.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tiradaus.infrastructure.persistence.jpa.entity.EventEntity;
import org.tiradaus.infrastructure.persistence.jpa.entity.EventUserEntity;
import org.tiradaus.infrastructure.persistence.jpa.entity.EventUserId;

import java.util.List;

public interface SpringDataUserEventRepository
        extends JpaRepository<EventUserEntity, EventUserId> {

    List<EventUserEntity> findByEvent_Id(Long eventId);
}