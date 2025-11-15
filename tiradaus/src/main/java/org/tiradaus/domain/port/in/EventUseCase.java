package org.tiradaus.domain.port.in;

import org.springframework.data.domain.Pageable;
import org.tiradaus.domain.model.EventMode;
import org.tiradaus.infrastructure.web.dto.EventPageResponse;
import org.tiradaus.infrastructure.web.dto.EventRequest;
import org.tiradaus.infrastructure.web.dto.EventResponse;
import java.util.List;

public interface EventUseCase {

    EventResponse create(EventRequest req);

    EventResponse getById(Long id);

    EventResponse update(Long id, EventRequest req);

    void delete(Long id);

    EventPageResponse<EventResponse> listPage(EventMode eventMode, Pageable pageable);

    List<EventResponse> listAll(EventMode eventMode);

    List<String> listModes();
}
