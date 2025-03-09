package nikitin.dao;

import nikitin.models.Payment;
import org.hibernate.SessionFactory;

public class PaymentDao extends RootDao<Payment, Short> {
    public PaymentDao(SessionFactory sessionFactory) {
        super(Payment.class, sessionFactory);
    }
}

