package nikitin.dao;

import nikitin.models.Customer;
import org.hibernate.SessionFactory;

public class CustomerDao extends RootDao<Customer, Short> {
    public CustomerDao(SessionFactory sessionFactory) {
        super(Customer.class, sessionFactory);
    }
}
