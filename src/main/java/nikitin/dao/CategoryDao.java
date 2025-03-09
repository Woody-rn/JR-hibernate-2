package nikitin.dao;

import nikitin.models.Category;
import org.hibernate.SessionFactory;

public class CategoryDao extends RootDao<Category, Byte> {
    public CategoryDao(SessionFactory sessionFactory) {
        super(Category.class, sessionFactory);
    }
}

