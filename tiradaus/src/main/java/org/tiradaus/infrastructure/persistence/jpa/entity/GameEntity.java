package org.tiradaus.infrastructure.persistence.jpa.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.tiradaus.domain.model.GameType;
import org.tiradaus.infrastructure.persistence.jpa.converter.GameTypeConverter;
import java.time.Instant;

@Entity
@Table(name = "games")
@Getter @Setter @NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class GameEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title", nullable = false, length = 100)
    private String title;

    @Column(name = "description", nullable = true)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "game_type", nullable = false, columnDefinition = "game_type")
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    private GameType gameType;

    @Column(name = "platform")
    private String platform;

    @Column(name = "min_age")
    private Integer minAge;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

    @PrePersist
    void onCreate() {
        Instant now = Instant.now();
        this.createdAt = now;
        this.updatedAt = now;
    }

    @PreUpdate
    void onUpdate() {
        this.updatedAt = Instant.now();
    }
}
