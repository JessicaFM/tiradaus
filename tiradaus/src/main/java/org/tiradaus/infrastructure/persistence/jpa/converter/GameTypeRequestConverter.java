package org.tiradaus.infrastructure.persistence.jpa.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.tiradaus.domain.model.GameType;

import java.util.Locale;

@Component
public class GameTypeRequestConverter implements Converter<String, GameType> {

    @Override
    public GameType convert(@Nullable String source) {
        if (source == null || source.isBlank()) return null;
        return GameType.valueOf(source.trim().toUpperCase(Locale.ROOT));
    }
}