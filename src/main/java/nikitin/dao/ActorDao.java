package nikitin.dao;

import nikitin.models.Actor;
import org.hibernate.SessionFactory;

public class ActorDao extends RootDao<Actor, Short> {
    public ActorDao(SessionFactory sessionFactory) {
        super(Actor.class, sessionFactory);
    }
}
