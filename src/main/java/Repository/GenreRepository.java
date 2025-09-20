package Repository;

import Entity.Author;
import Entity.Genre;
import Util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;


import java.util.List;

public class GenreRepository {

    public Genre create(Genre genre) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(genre);
            transaction.commit();
            return genre;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return null;
        }
    }

    public List<Genre> findByName(String name) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Genre g WHERE g.name = :name", Genre.class)
                    .setParameter("name", name)
                    .list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Genre read(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Genre.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Genre update(Genre genre) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.merge(genre);
            transaction.commit();
            return genre;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return null;
        }
    }

    public boolean delete(Genre genre) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.remove(genre);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return false;
        }
    }

    public List<Genre> findAll() {
        Transaction transaction = null;
        List<Genre> genres = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            genres = session.createQuery("FROM Genre", Genre.class).list();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }

        return genres;
    }

    public void printAllGenres(GenreRepository genreRepo) {
        List<Genre> genres = genreRepo.findAll();

        if (genres == null || genres.isEmpty()) {
            System.out.println("No genres found.");
            return;
        }

        System.out.println("\n--- All Genres ---");
        for (Genre g : genres) {
            System.out.println("ID: " + g.getId());
            System.out.println("Name: " + g.getName());
            System.out.println("------------------");
        }
    }
}
