package nikitin.models.convertors;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.time.Year;
import java.util.Objects;
import java.util.Optional;

@Converter(autoApply = true)
public class YearConvertor implements AttributeConverter<Year, Short> {
    @Override
    public Short convertToDatabaseColumn(Year attribute) {
        return Optional.ofNullable(attribute)
                .map(Year::getValue)
                .map(Integer::shortValue)
                .orElse(null);
    }

    @Override
    public Year convertToEntityAttribute(Short dbData) {
        return Optional.ofNullable(dbData)
                .map(Year::of)
                .orElse(null);
    }
}
