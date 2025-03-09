package nikitin.dao;

import nikitin.models.Rental;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class RentalDao extends RootDao<Rental, Integer> {
    public RentalDao(SessionFactory sessionFactory) {
        super(Rental.class, sessionFactory);
    }

    public Rental getAnyRentedItem() {
        Query<Rental> query = getCurrentSession()
                .createQuery("select r from Rental r where r.returnDate IS NULL", getEntityClazz());
        query.setMaxResults(1);
        return query.uniqueResult();
    }
}
