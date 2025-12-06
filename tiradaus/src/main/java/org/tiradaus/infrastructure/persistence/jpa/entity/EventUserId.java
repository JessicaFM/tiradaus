package org.tiradaus.infrastructure.persistence.jpa.entity;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class EventUserId implements Serializable {

    private Long eventId;
    private Long userId;
}