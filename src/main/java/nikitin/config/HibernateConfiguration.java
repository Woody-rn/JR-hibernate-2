package nikitin.config;

import nikitin.models.*;
import org.hibernate.cfg.Configuration;

import java.util.Objects;

public class HibernateConfiguration {
    private static Configuration configuration;

    public static Configuration get() {
        if (Objects.isNull(configuration)) {
            configuration = new Configuration()
                    .addAnnotatedClass(Actor.class)
                    .addAnnotatedClass(Address.class)
                    .addAnnotatedClass(Category.class)
                    .addAnnotatedClass(City.class)
                    .addAnnotatedClass(Country.class)
                    .addAnnotatedClass(Customer.class)
                    .addAnnotatedClass(Film.class)
                    .addAnnotatedClass(FilmText.class)
                    .addAnnotatedClass(Inventory.class)
                    .addAnnotatedClass(Language.class)
                    .addAnnotatedClass(Payment.class)
                    .addAnnotatedClass(Rental.class)
                    .addAnnotatedClass(Staff.class)
                    .addAnnotatedClass(Store.class);
        }
        return configuration;
    }

    private HibernateConfiguration() {}
}
