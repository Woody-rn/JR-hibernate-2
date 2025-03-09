package nikitin.models.convertors;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import nikitin.models.Rating;

@Converter(autoApply = true)
public class RatingConvertor implements AttributeConverter<Rating, String> {
    @Override
    public Rating convertToEntityAttribute(String dbData) {
        Rating[] values = Rating.values();
        for (Rating rating : values) {
            if(rating.getValue().equals(dbData)) {
                return rating;
            }
        }
        return null;
    }

    @Override
    public String convertToDatabaseColumn(Rating attribute) {
        return attribute.getValue();
    }
}
