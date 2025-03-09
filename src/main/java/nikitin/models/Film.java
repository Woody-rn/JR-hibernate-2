package nikitin.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import nikitin.models.convertors.RatingConvertor;
import nikitin.models.convertors.YearConvertor;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.*;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity
@Table(name = "film", schema = "movie")
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "film_id")
    private Short id;

    @Column(length = 128, nullable = false)
    private String title;

    @Column(columnDefinition = "text")
    private String description;

    @Column(name = "release_year", columnDefinition = "year")
    @Convert(converter = YearConvertor.class)
    private Year releaseYear;

    @ManyToOne
    @JoinColumn(name = "language_id", nullable = false)
    private Language language;

    @ManyToOne
    @JoinColumn(name = "original_language_id")
    private Language originalLanguage;

    @Column(name = "rental_duration", nullable = false)
    private Byte rentalDuration;

    @Column(name = "rental_rate", precision = 4, scale = 2, nullable = false)
    private BigDecimal rentalRate;

    private Short length;

    @Column(name = "replacement_cost", precision = 5, scale = 2, nullable = false)
    private BigDecimal replacementCost;

    @Column(columnDefinition = "enum('G', 'PG', 'PG-13', 'R', 'NC-17')")
    @Convert(converter = RatingConvertor.class)
    private Rating rating;

    @Column(name = "special_features", columnDefinition = "set('Trailers', 'Commentaries', 'Deleted Scenes', 'Behind the Scenes')")
    private String specialFeatures;

    @Column(name = "last_update", nullable = false)
    @UpdateTimestamp
    private LocalDateTime lastUpdate;

    @ManyToMany
    @JoinTable(name = "film_actor",
            schema = "movie",
            joinColumns = @JoinColumn(name = "film_id", referencedColumnName = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id", referencedColumnName = "actor_id"))
    private Set<Actor> actors;

    @ManyToMany
    @JoinTable(name = "film_category",
            schema = "movie",
            joinColumns = @JoinColumn(name = "film_id", referencedColumnName = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "category_id"))
    private Set<Category> categories;

    public Set<SpecialFeature> getSpecialFeatures() {
        if (Objects.isNull(specialFeatures) || specialFeatures.isEmpty()) {
            return null;
        }

        return Arrays.stream(specialFeatures.split(","))
                .map(SpecialFeature::getSpecialFeatureByValue)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
    }

    public void setSpecialFeatures(Set<SpecialFeature> features) {
        if(Objects.isNull(features)){
            specialFeatures = null;
        } else {
            specialFeatures = features.stream()
                    .map(SpecialFeature::getValue)
                    .collect(Collectors.joining(","));
        }
    }
}
