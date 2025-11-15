package org.tiradaus.infrastructure.web.controller;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.tiradaus.domain.model.EventMode;
import org.tiradaus.domain.model.GameType;
import org.tiradaus.domain.port.in.EventUseCase;
import org.tiradaus.infrastructure.web.dto.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
@Tag(
        name = "Events",
        description = "Events actions with JWT token issuance",
        externalDocs = @ExternalDocumentation(
                description = "Events flow details",
                url = "https://github.com/JessicaFM/tiradaus"
        )
)
public class EventController {

    private final EventUseCase eventUseCase;

    public EventController(EventUseCase eventUseCase) {
        this.eventUseCase = eventUseCase;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponse(responseCode = "201", description = "Created")
    @ApiResponse(responseCode = "400", description = "Invalid request")
    @ApiResponse(responseCode = "403", description = "Forbidden - requires ADMIN")
    @Operation(
            summary = "Create new event",
            description = "Endpoint to create new event"
    )
    public EventResponse create(@Valid @RequestBody EventRequest request) {
        return eventUseCase.create(request);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Get a event by ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK",
                            content = @Content(schema = @Schema(implementation = EventResponse.class))),
                    @ApiResponse(responseCode = "404", description = "Gmae Not found")
            }
    )
    public EventResponse getById(@PathVariable Long id) {
        return eventUseCase.getById(id);
    }

    @PutMapping("/{id}")
    @ApiResponse(responseCode = "403", description = "Forbidden - requires ADMIN")
    @Operation(
            summary = "Update event",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Update game",
                            content = @Content(schema = @Schema(implementation = EventResponse.class))),
                    @ApiResponse(responseCode = "400", description = "Invàlid petition"),
                    @ApiResponse(responseCode = "404", description = "Not found")
            }
    )
    public EventResponse update(@PathVariable Long id, @Valid @RequestBody EventRequest request) {
        return eventUseCase.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "403", description = "Forbidden - requires ADMIN")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(
            summary = "Delete Event",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Deleted"),
                    @ApiResponse(responseCode = "404", description = "Not found")
            }
    )
    public void delete(@PathVariable Long id) {
        eventUseCase.delete(id);
    }

    @GetMapping
    @Operation(
            summary = "List events (paginated & filter)",
            description = """
            Optional filter:
            - event_mode: type of event (online, real_life)
            """)
    public EventPageResponse<EventResponse> listPage(
            @Parameter(description = "Filter by event mode (online/real_life)")
            @RequestParam(required = false) EventMode eventMode,
            @ParameterObject
            @PageableDefault(size = 20, sort = "id") Pageable pageable
    ) {
        return eventUseCase.listPage(eventMode, pageable);
    }


    @GetMapping("/all")
    @Operation(
            summary = "List events (no pageable)",
            description = """
            Optional filter:
            - game_type: type of event (online, real_life)
            """)
    public List<EventResponse> list(
            @Parameter(description = "Filter by event mode (online/real_life)")
            @RequestParam(required = false, name = "event_mode") EventMode eventMode
    ) {
        return eventUseCase.listAll(eventMode);
    }
}
