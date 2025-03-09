package nikitin.dao;

import nikitin.models.Staff;
import org.hibernate.SessionFactory;

public class StaffDao extends RootDao<Staff, Byte> {
    public StaffDao(SessionFactory sessionFactory) {
        super(Staff.class, sessionFactory);
    }
}
