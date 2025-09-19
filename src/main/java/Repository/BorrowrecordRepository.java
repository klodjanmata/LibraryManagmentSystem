package Repository;

import Entity.BorrowRecord;
import Util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.function.Consumer;

public class BorrowrecordRepository {

    public void save(BorrowRecord record) {
        executeTransaction(session -> session.persist(record));
    }

    public void update(BorrowRecord record) {
        executeTransaction(session -> session.merge(record));
    }

    public void delete(BorrowRecord record) {
        executeTransaction(session -> session.remove(record));
    }

    public BorrowRecord findById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(BorrowRecord.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<BorrowRecord> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM BorrowRecord", BorrowRecord.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }

    private void executeTransaction(Consumer<Session> action) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            action.accept(session);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }
}
