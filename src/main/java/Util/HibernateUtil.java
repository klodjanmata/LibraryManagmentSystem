package Util;

import lombok.Getter;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import Entity.Author;
import Entity.Book;
import Entity.Genre;
import Entity.Member;

public class HibernateUtil {
    @Getter
    private static final SessionFactory sessionFactory = buildSessionFactory();

    public static SessionFactory buildSessionFactory() {
        try {
            return new Configuration()
                    .configure("hibernate.cfg.xml") // Load hibernate.cfg.xml
                    .addAnnotatedClass(Author.class) // Add all entities
                    .addAnnotatedClass(Book.class)
                    .addAnnotatedClass(Genre.class)
                    .addAnnotatedClass(Member.class)
                    .buildSessionFactory();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void shutdown() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}
