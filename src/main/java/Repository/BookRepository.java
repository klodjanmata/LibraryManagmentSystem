package Repository;

import Entity.Book;
import Util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class BookRepository {

    public Book create(Book book){
            try(Session session = HibernateUtil.getSessionFactory().openSession()){
                Transaction transaction = session.beginTransaction();
                session.persist(book);
                transaction.commit();
                return book;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return book;
        }

        public Book read(Long id) {
            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                return session.get(Book.class,id);
            }
        }

        public Book update(Book book){
            try(Session session = HibernateUtil.getSessionFactory().openSession()){
                Transaction transaction = session.beginTransaction();
                session.merge(book);
                transaction.commit();
                return book;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return book;
        }

        public Book delete(Book book){
            try(Session session = HibernateUtil.getSessionFactory().openSession()){
                Transaction transaction = session.beginTransaction();
                session.remove(book);
                transaction.commit();
                return book;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return book;
        }

        public List<Book> findAll() {
            Transaction transaction = null;
            List<Book> books = null;

            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                transaction = session.beginTransaction();

                books = session.createQuery("from Book", Book.class).list();

                transaction.commit();
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                e.printStackTrace();
            }

            return books;
        }

    public Book findById(Long bookId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Book.class, bookId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
