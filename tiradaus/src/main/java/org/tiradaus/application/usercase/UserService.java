package org.tiradaus.application.usercase;

import org.springframework.stereotype.Service;
import org.tiradaus.domain.port.in.UserUseCase;
import org.tiradaus.infrastructure.persistence.jpa.entity.UserEntity;
import org.tiradaus.infrastructure.persistence.jpa.repository.SpringDataUserRepository;
import org.tiradaus.infrastructure.web.dto.UserRequest;
import org.tiradaus.infrastructure.web.dto.UserResponse;
import java.util.List;

@Service
public class UserService implements UserUseCase {
    private final SpringDataUserRepository userRepository;

    public UserService(SpringDataUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserResponse getById(Long id) {
        return userRepository.findById(id)
                .map(this::toResponse)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public List<UserResponse> getAll() {
        return userRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public UserResponse update(Long id, UserRequest request) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (request.getUserName() != null) {
            user.setUserName(request.getUserName());
        }
        if (request.getFirstName() != null) {
            user.setFirstName(request.getFirstName());
        }
        if (request.getLastName() != null) {
            user.setLastName(request.getLastName());
        }
        if (request.getEmail() != null) {
            user.setEmail(request.getEmail());
        }
        if (request.getBirthDate() != null) {
            user.setBirthDate(request.getBirthDate());
        }

        return toResponse(userRepository.save(user));
    }

    private UserResponse toResponse(UserEntity user) {
        return new UserResponse(
                user.getId(),
                user.getUserName(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getIsActive(),
                user.getBirthDate()
        );
    }
}
