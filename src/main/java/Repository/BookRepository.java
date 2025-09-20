package Repository;

import Entity.Book;
import Util.HibernateUtil;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class BookRepository {

    public Book create(Book book) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(book);
            transaction.commit();
            return book;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
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
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Book merged = (Book) session.merge(book);
            transaction.commit();
            return merged;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return null;
        }
    }

    public Book delete(Book book) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.remove(book);
            transaction.commit();
            return book;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return null;
        }
    }

    public List<Book> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<Book> books = session.createQuery("FROM Book", Book.class).list();
            for (Book book : books) {
                Hibernate.initialize(book.getAuthors());
                Hibernate.initialize(book.getGenres());
            }
            return books;
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }

    public Book findById(Long bookId) {
        return read(bookId);
    }

    public void printAllBooks() {
        List<Book> books = findAll();
        if (books == null || books.isEmpty()) {
            System.out.println("No books found.");
            return;
        }

        System.out.println("\n--- All Books ---");
        for (Book b : books) {
            System.out.println("ID: " + b.getId());
            System.out.println("Title: " + b.getTitle());
            System.out.println("Description: " + b.getDescription());
            if (b.getAuthors() != null && !b.getAuthors().isEmpty()) {
                System.out.print("Authors: ");
                b.getAuthors().forEach(a -> System.out.print(a.getName() + " "));
                System.out.println();
            }
            if (b.getGenres() != null && !b.getGenres().isEmpty()) {
                System.out.print("Genres: ");
                b.getGenres().forEach(g -> System.out.print(g.getName() + " "));
                System.out.println();
            }
            System.out.println("---------------------");
        }
    }

    public List<Book> findByTitle(String title, BookRepository bookRepository) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                            "FROM Book b WHERE b.title = :title", Book.class)
                    .setParameter("title", title)
                    .list();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }

}
