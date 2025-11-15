package org.tiradaus.domain.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum GameType {
    ONLINE,
    PHYSICAL;

    @JsonCreator
    public static GameType fromJson(String value) {
        if (value == null) return null;
        return GameType.valueOf(value.trim().toUpperCase());
    }

    @JsonValue
    public String toJson() {
        return name();
    }
}
