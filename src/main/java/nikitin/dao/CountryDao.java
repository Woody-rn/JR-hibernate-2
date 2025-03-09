package nikitin.dao;

import nikitin.models.Country;
import org.hibernate.SessionFactory;

public class CountryDao extends RootDao<Country, Short> {
    public CountryDao(SessionFactory sessionFactory) {
        super(Country.class, sessionFactory);
    }
}
