package nikitin.models;

import lombok.Getter;

@Getter
public enum SpecialFeature {
    TRAILERS("Trailers"),
    COMMENTARIES("Commentaries"),
    DELETED_SCENES("Deleted Scenes"),
    BEHIND_THE_SCENES("Behind the Scenes");

    private String value;

    SpecialFeature(String value) {
        this.value = value;
    }

    public static SpecialFeature getSpecialFeatureByValue(String value) {
        if (value == null) {
            return null;
        }
        for (SpecialFeature feature : values()) {
            if (feature.value.equals(value)) {
                return feature;
            }
        }
        return null;
    }
}
