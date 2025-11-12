package org.tiradaus.infrastructure.persistence.jpa.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.tiradaus.domain.model.GameType;

import java.util.Locale;

@Converter(autoApply = false)
public class GameTypeConverter implements AttributeConverter<GameType, String> {

    @Override
    public String convertToDatabaseColumn(GameType attribute) {
        return attribute == null ? null : attribute.name().toLowerCase(Locale.ROOT);
    }

    @Override
    public GameType convertToEntityAttribute(String dbData) {
        return dbData == null ? null : GameType.valueOf(dbData.trim().toUpperCase(Locale.ROOT));
    }
}