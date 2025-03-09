package nikitin.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public abstract class RootDao<T, K extends Serializable> {
    private final Class<T> clazz;
    private final SessionFactory sessionFactory;

    public RootDao(Class<T> clazz, SessionFactory sessionFactory) {
        this.clazz = clazz;
        this.sessionFactory = sessionFactory;
    }

    public Optional<T> findById(final K id) {
        try (Session session = getCurrentSession()) {
            T ob = (T) session.get(clazz, id);
            return Optional.ofNullable(ob);
        }
    }

    public List<T> getAll() {
        return getCurrentSession().createQuery("from " + clazz.getName(), clazz)
                .list();
    }

    public List<T> getItems(int from, int count) {
        return getCurrentSession().createQuery("from " + clazz.getName(), clazz)
                .setFirstResult(from)
                .setMaxResults(count)
                .list();
    }

    public void save(final T entity) {
            getCurrentSession().save(entity);
    }

    public void update(final T entity) {

    }

    public void delete(final T entity) {
        getCurrentSession().remove(entity);

    }
    public void deleteById(final K entityId) {
        findById(entityId).ifPresent(this::delete);
    }

    protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    protected Class<T> getEntityClazz() {
        return clazz;
    }
}
