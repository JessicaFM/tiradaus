package org.tiradaus.infrastructure.persistence.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tiradaus.infrastructure.persistence.jpa.entity.RoleEntity;
import java.util.Optional;

public interface SpringDataRoleRepository extends JpaRepository<RoleEntity, Long> {
    Optional<RoleEntity> findByName(String name);
}
