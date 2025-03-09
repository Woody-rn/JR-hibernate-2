package nikitin.dao;

import nikitin.models.Inventory;
import org.hibernate.SessionFactory;

public class InventoryDao extends RootDao<Inventory, Integer> {
    public InventoryDao(SessionFactory sessionFactory) {
        super(Inventory.class, sessionFactory);
    }
}
