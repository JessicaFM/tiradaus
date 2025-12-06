package org.tiradaus.application.usercase;

import org.springframework.stereotype.Service;
import org.tiradaus.domain.port.in.EventUserUseCase;
import org.tiradaus.infrastructure.persistence.jpa.entity.EventEntity;
import org.tiradaus.infrastructure.persistence.jpa.entity.EventUserEntity;
import org.tiradaus.infrastructure.persistence.jpa.entity.EventUserId;
import org.tiradaus.infrastructure.persistence.jpa.entity.UserEntity;
import org.tiradaus.infrastructure.persistence.jpa.repository.SpringDataEventRepository;
import org.tiradaus.infrastructure.persistence.jpa.repository.SpringDataUserEventRepository;
import org.tiradaus.infrastructure.persistence.jpa.repository.SpringDataUserRepository;
import org.tiradaus.infrastructure.web.dto.EventUserResponse;
import java.util.List;

@Service
public class EventUserService implements EventUserUseCase {
    private final SpringDataUserEventRepository eventUserRepository;
    private final SpringDataUserRepository userRepository;
    private final SpringDataEventRepository eventRepository;

    public EventUserService(
            SpringDataUserEventRepository eventUserRepository,
            SpringDataUserRepository userRepository,
            SpringDataEventRepository eventRepository
    ) {
        this.eventUserRepository = eventUserRepository;
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
    }

    @Override
    public void addUserToEvent(Long eventId, Long userId) {
        EventUserId id = new EventUserId(eventId, userId);

        if (eventUserRepository.existsById(id)) {
            return;
        }

        EventEntity event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        EventUserEntity relation = new EventUserEntity();
        relation.setId(id);
        relation.setEvent(event);
        relation.setUser(user);

        eventUserRepository.save(relation);
    }

    @Override
    public EventUserResponse getUsersByEvent(Long eventId) {
        EventEntity event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        List<EventUserEntity> eventUsers = eventUserRepository.findByEvent_Id(eventId);

        List<EventUserResponse.UserInEvent> users = eventUsers.stream()
                .map(EventUserEntity::getUser)
                .map(u -> new EventUserResponse.UserInEvent(
                        u.getId(),
                        u.getUserName(),
                        u.getFirstName()
                ))
                .toList();

        Long gameId = null;
        String gameTitle = null;
        String gameImageUrl = null;

        if (event.getGame() != null) {
            gameId = event.getGame().getId();
            gameTitle = event.getGame().getTitle();
            gameImageUrl = event.getGame().getImageUrl();
        }

        return new EventUserResponse(
                event.getId(),
                event.getName(),
                event.getStartDate(),
                event.getEndDate(),
                gameId,
                gameTitle,
                gameImageUrl,
                users
        );
    }
}