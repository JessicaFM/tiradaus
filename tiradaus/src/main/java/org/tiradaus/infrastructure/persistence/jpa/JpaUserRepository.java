package org.tiradaus.infrastructure.persistence.jpa;

import org.springframework.stereotype.Repository;
import org.tiradaus.domain.model.Role;
import org.tiradaus.domain.model.User;
import org.tiradaus.domain.port.out.LoadUserPort;
import org.tiradaus.domain.port.out.SaveUserPort;
import org.tiradaus.infrastructure.persistence.jpa.entity.RoleEntity;
import org.tiradaus.infrastructure.persistence.jpa.entity.UserEntity;
import org.tiradaus.infrastructure.persistence.jpa.mapper.UserJpaMapper;
import org.tiradaus.infrastructure.persistence.jpa.repository.SpringDataRoleRepository;
import org.tiradaus.infrastructure.persistence.jpa.repository.SpringDataUserRepository;
import java.util.Optional;

@Repository
public class JpaUserRepository implements LoadUserPort, SaveUserPort {

    private final SpringDataUserRepository userRepo;
    private final SpringDataRoleRepository roleRepo;
    private final UserJpaMapper mapper;

    public JpaUserRepository(SpringDataUserRepository userRepo, SpringDataRoleRepository roleRepo, UserJpaMapper mapper) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.mapper = mapper;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepo.findByEmail(email).map(mapper::toDomain);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepo.findByUserName(username).map(mapper::toDomain);
    }

    @Override
    public void save(User user) {
//        UserEntity entity = mapper.toEntity(user);
//        Long rolId = user.roleId()!= null ? user.roleId() : 2L;

        UserEntity entity = mapper.toEntity(user);
        userRepo.save(entity);
    }


}
