package nikitin.dao;

import nikitin.models.Film;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class FilmDao extends RootDao<Film, Short> {
    public FilmDao(SessionFactory sessionFactory) {
        super(Film.class, sessionFactory);
    }

    public Film getAnyAvailableFilmForRental() {
        Query<Film> query = getCurrentSession().createQuery("select f from Film f " +
                "where f.id not in(select distinct i.film.id from Inventory i)", Film.class);
        query.setMaxResults(1);
        return query.uniqueResult();
    }
}