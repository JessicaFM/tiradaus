package org.tiradaus.infrastructure.persistence.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tiradaus.infrastructure.persistence.jpa.entity.UserEntity;
import java.util.Optional;

public interface SpringDataUserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> findByUserName(String userName);
    boolean existsByUserName(String userName);
    boolean existsByEmail(String email);
}
