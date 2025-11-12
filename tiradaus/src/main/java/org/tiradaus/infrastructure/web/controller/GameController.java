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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.tiradaus.domain.model.GameType;
import org.tiradaus.domain.port.in.GameUseCase;
import org.tiradaus.infrastructure.web.dto.GameRequest;
import org.tiradaus.infrastructure.web.dto.GameResponse;
import org.tiradaus.infrastructure.web.dto.PageResponse;

import java.util.List;

@RestController
@RequestMapping("/api/games")
@Tag(
        name = "Games",
        description = "Games actions with JWT token issuance",
        externalDocs = @ExternalDocumentation(
                description = "Games flow details",
                url = "https://github.com/JessicaFM/tiradaus"
        )
)
public class GameController {

    private final GameUseCase gameUseCase;

    public GameController(GameUseCase gameUseCase) {
        this.gameUseCase = gameUseCase;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN')")
    @ApiResponse(responseCode = "201", description = "Created")
    @ApiResponse(responseCode = "400", description = "Invalid request")
    @ApiResponse(responseCode = "403", description = "Forbidden - requires ADMIN")
    @Operation(
            summary = "Create new game",
            description = "Endpoint to create new game"
    )
    public GameResponse create(@Valid @RequestBody GameRequest request) {
        return gameUseCase.create(request);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Get a game by ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK",
                            content = @Content(schema = @Schema(implementation = GameResponse.class))),
                    @ApiResponse(responseCode = "404", description = "Gmae Not found")
            }
    )
    public GameResponse getById(@PathVariable Long id) {
        return gameUseCase.getById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @ApiResponse(responseCode = "403", description = "Forbidden - requires ADMIN")
    @Operation(
            summary = "Update all game",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Update game",
                            content = @Content(schema = @Schema(implementation = GameResponse.class))),
                    @ApiResponse(responseCode = "400", description = "Invàlid petition"),
                    @ApiResponse(responseCode = "404", description = "Not found")
            }
    )
    public GameResponse update(@PathVariable Long id, @Valid @RequestBody GameRequest request) {
        return gameUseCase.update(id, request);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @ApiResponse(responseCode = "403", description = "Forbidden - requires ADMIN")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(
            summary = "Delete Game",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Deleted"),
                    @ApiResponse(responseCode = "404", description = "Not found")
            }
    )
    public void delete(@PathVariable Long id) {
        gameUseCase.delete(id);
    }

    @GetMapping
    @Operation(
            summary = "List games (paginated & filter)",
            description = """
            Optional filter:
            - game_type: type of game (online, physical)
            """)
    public PageResponse<GameResponse> listPage(
            @Parameter(description = "Filter by game type (online/physical)")
            @RequestParam(required = false) GameType gameType,
            @ParameterObject
            @PageableDefault(size = 20, sort = "createdAt") Pageable pageable
    ) {
        return gameUseCase.listPage(gameType, pageable);
    }

    @GetMapping("/all")
    @Operation(
            summary = "List games (no pageable)",
            description = """
            Optional filter:
            - game_type: type of game (online, physical)
            """)
    public List<GameResponse> list(
            @Parameter(description = "Filter by game type (online/physical)")
            @RequestParam(required = false, name = "game_type") GameType gameType
    ) {
        return gameUseCase.listAll(gameType);
    }
}
