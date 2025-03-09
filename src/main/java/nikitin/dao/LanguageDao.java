package nikitin.dao;

import nikitin.models.Language;
import org.hibernate.SessionFactory;

public class LanguageDao extends RootDao<Language, Byte> {
    public LanguageDao(SessionFactory sessionFactory) {
        super(Language.class, sessionFactory);
    }
}
