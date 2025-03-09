package nikitin.dao;

import nikitin.models.Address;
import org.hibernate.SessionFactory;

public class AddressDao extends RootDao<Address, Short> {
    public AddressDao(SessionFactory sessionFactory) {
        super(Address.class, sessionFactory);
    }
}
