package org.tiradaus.application.usercase;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.tiradaus.domain.port.in.RegisterUserUseCase;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.tiradaus.infrastructure.persistence.jpa.entity.RoleEntity;
import org.tiradaus.infrastructure.persistence.jpa.entity.UserEntity;
import org.tiradaus.infrastructure.persistence.jpa.repository.SpringDataUserRepository;
import org.tiradaus.infrastructure.web.dto.RegisterRequest;
import org.tiradaus.infrastructure.web.dto.RegisterResponse;

import java.time.Instant;

@Service
public class RegisterUserService implements RegisterUserUseCase {

    private static final long SELF_REGISTER_ROLE_ID = 2L;

    private final SpringDataUserRepository springDataUserRepository;
    private final PasswordEncoder passwordEncoder;

    public RegisterUserService(SpringDataUserRepository springDataUserRepository, PasswordEncoder passwordEncoder){
        this.springDataUserRepository = springDataUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public RegisterResponse register(RegisterRequest req) {
        String username = req.getUsername();
        String email = req.getEmail().trim().toLowerCase();

        System.out.println(req.getPassword());
        System.out.println(req.getEmail());
        System.out.println(req.getFirstName());
        System.out.println(req.getUsername());


        if(springDataUserRepository.existsByUserName(username)) {
            throw new IllegalArgumentException("Username already exists");
        }

        if(springDataUserRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("Email already exists");
        }

        long roleId = (req.getRoleId() == null) ? SELF_REGISTER_ROLE_ID : req.getRoleId();
        System.out.println("roleId: " + roleId);
        System.out.println("SELF_REGISTER_ROLE_ID: " + SELF_REGISTER_ROLE_ID);

        if(roleId != SELF_REGISTER_ROLE_ID) {
            throw new IllegalArgumentException("Invalid role");
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setUserName(username);
        userEntity.setFirstName(req.getFirstName());
        userEntity.setLastName(req.getLastName());
        userEntity.setEmail(email);
        userEntity.setPassword(passwordEncoder.encode(req.getPassword()));
        userEntity.setIsActive(true);

        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setId(roleId);

        userEntity.setRole(roleEntity);

        userEntity.setBirthDate(req.getBirthDate());

        userEntity.setCreatedAt(Instant.now());
        userEntity.setUpdatedAt(Instant.now());

        UserEntity saveUser = springDataUserRepository.save(userEntity);

        return new RegisterResponse(
                saveUser.getId(),
                saveUser.getUserName(),
                saveUser.getEmail(),
                saveUser.getRole().getId(),
                saveUser.getIsActive(),
                saveUser.getBirthDate()
        );
    }
}
