package nikitin.dao;

import nikitin.models.Store;
import org.hibernate.SessionFactory;

public class StoreDao extends RootDao<Store, Byte> {
    public StoreDao(SessionFactory sessionFactory) {
        super(Store.class, sessionFactory);
    }
}
