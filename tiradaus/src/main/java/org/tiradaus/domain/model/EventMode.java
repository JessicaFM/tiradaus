package org.tiradaus.domain.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum EventMode {
    REAL_LIFE,
    ONLINE;

    @JsonCreator
    public static EventMode fromJson(String value) {
        if (value == null) return null;
        return EventMode.valueOf(value.trim().toUpperCase());
    }

    @JsonValue
    public String toJson() {
        return name();
    }
}
