package Repository;

import Entity.Author;
import Util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class AuthorRepository {

    // Create new author
    public Author create(Author author) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(author);
            transaction.commit();
            return author;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return null;
        }
    }

    // Read author by ID
    public Author read(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Author.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Update author
    public Author update(Author author) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Author merged = (Author) session.merge(author);
            transaction.commit();
            return merged;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return null;
        }
    }

    // Delete author
    public Author delete(Author author) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.remove(author);
            transaction.commit();
            return author;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return null;
        }
    }

    // Find all authors
    public List<Author> findAll() {
        Transaction transaction = null;
        List<Author> authors = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            authors = session.createQuery("FROM Author", Author.class).list();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }

        return authors;
    }

    public List<Author> findByName(String name) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Author a WHERE lower(a.name) LIKE :name", Author.class)
                    .setParameter("name", "%" + name.toLowerCase() + "%")
                    .list();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }
}
