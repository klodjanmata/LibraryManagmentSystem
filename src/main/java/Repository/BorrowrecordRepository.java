package Repository;

import Entity.BorrowRecord;
import Util.HibernateUtil;
import org.hibernate.Hibernate;
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

    public List<BorrowRecord> findByMemberName(String memberName) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<BorrowRecord> records = session.createQuery(
                            "FROM BorrowRecord br WHERE br.member.name = :name", BorrowRecord.class)
                    .setParameter("name", memberName)
                    .list();

            for (BorrowRecord record : records) {
                Hibernate.initialize(record.getBook());
                Hibernate.initialize(record.getBook().getAuthors());
                Hibernate.initialize(record.getBook().getGenres());
                Hibernate.initialize(record.getMember());
            }

            return records;
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }

    public List<BorrowRecord> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<BorrowRecord> records = session.createQuery("FROM BorrowRecord", BorrowRecord.class).list();

            for (BorrowRecord record : records) {
                Hibernate.initialize(record.getBook());
                Hibernate.initialize(record.getBook().getAuthors());
                Hibernate.initialize(record.getBook().getGenres());
                Hibernate.initialize(record.getMember());
            }
            return records;
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
