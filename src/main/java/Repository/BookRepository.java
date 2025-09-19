package Repository;

import Entity.Book;
import Util.HibernateUtil;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class BookRepository {

    public Book create(Book book) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(book);
            transaction.commit();
            return book;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Book read(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Book book = session.get(Book.class, id);
            if (book != null) {
                Hibernate.initialize(book.getAuthors());
                Hibernate.initialize(book.getGenres());
            }
            return book;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Book update(Book book) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Book mergedBook = (Book) session.merge(book);
            transaction.commit();
            return mergedBook;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Delete a Book
    public Book delete(Book book) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.remove(book);
            transaction.commit();
            return book;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public List<Book> findAll() {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<Book> books = session.createQuery("from Book", Book.class).list();

            for (Book book : books) {
                Hibernate.initialize(book.getAuthors());
                Hibernate.initialize(book.getGenres());
            }
            return books;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Book findById(Long bookId) {
        return read(bookId);
    }
}
