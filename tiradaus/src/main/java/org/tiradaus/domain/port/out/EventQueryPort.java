package org.tiradaus.domain.port.out;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.tiradaus.domain.model.Event;
import org.tiradaus.domain.model.EventMode;

import java.util.List;

public interface EventQueryPort {
    Page<Event> findAll(Pageable pageable);
    Page<Event> findByMode(EventMode eventMode, Pageable pageable);
    List<Event> findAll();
    List<Event> findAllByMode(EventMode eventMode);
}
