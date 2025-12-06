package org.tiradaus.infrastructure.web.controller;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.tiradaus.domain.port.in.EventUserUseCase;
import org.tiradaus.infrastructure.persistence.jpa.entity.EventEntity;
import org.tiradaus.infrastructure.persistence.jpa.repository.SpringDataEventRepository;
import org.tiradaus.infrastructure.web.dto.EventUserResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/events")
@Tag(
        name = "Event with users",
        description = "Event with users actions with JWT token issuance",
        externalDocs = @ExternalDocumentation(
                description = "Event with users flow details",
                url = "https://github.com/JessicaFM/tiradaus"
        )
)
public class EventUserController {
    private final EventUserUseCase eventUserUseCase;
    private final SpringDataEventRepository eventRepository;

    public EventUserController(EventUserUseCase eventUserUseCase,
                               SpringDataEventRepository eventRepository) {
        this.eventUserUseCase = eventUserUseCase;
        this.eventRepository = eventRepository;
    }

    @GetMapping("/{eventId}/users")
    public ResponseEntity<EventUserResponse> getUsersByEvent(
            @PathVariable Long eventId
    ) {
        return ResponseEntity.ok(eventUserUseCase.getUsersByEvent(eventId));
    }

    @PostMapping("/{eventId}/users/{userId}")
    public ResponseEntity<Void> addUserToEvent(
            @PathVariable Long eventId,
            @PathVariable Long userId
    ) {
        eventUserUseCase.addUserToEvent(eventId, userId);
        return ResponseEntity.ok().build();
    }
}