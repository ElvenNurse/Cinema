package mate.academy.cinema.util;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static SessionFactory sessionFactory = initSessionFactory();

    private HibernateUtil() {
    }

    private static SessionFactory initSessionFactory() {
        try {
            return new Configuration().configure().buildSessionFactory();
        } catch (HibernateException e) {
            throw new RuntimeException("Error while creating Session Factory :", e);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
