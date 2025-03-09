package nikitin.dao;

import nikitin.models.FilmText;
import org.hibernate.SessionFactory;

public class FilmTextDao extends RootDao<FilmText, Short> {
    public FilmTextDao(SessionFactory sessionFactory) {
        super(FilmText.class, sessionFactory);
    }
}
