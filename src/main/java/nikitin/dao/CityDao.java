package nikitin.dao;

import nikitin.models.City;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class CityDao extends RootDao<City, Short> {
    public CityDao(SessionFactory sessionFactory) {
        super(City.class, sessionFactory);
    }

    public City findByName(String name) {
        Query<City> query = getCurrentSession()
                .createQuery("from City c where c.city = :name", getEntityClazz());
        query.setParameter("name", name);
        return query.uniqueResult();

    }
}
