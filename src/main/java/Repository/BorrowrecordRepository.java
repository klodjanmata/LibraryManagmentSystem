package Repository;

import Entity.Author;
import Entity.BorrowRecord;
import Util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class BorrowrecordRepository {

    public BorrowRecord create(BorrowRecord borrowRecord){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            session.persist(borrowRecord);
            transaction.commit();
            return borrowRecord;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return borrowRecord;
    }

    public BorrowRecord read(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(BorrowRecord.class,id);
        }
    }

    public BorrowRecord update(BorrowRecord borrowRecord){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            session.merge(borrowRecord);
            transaction.commit();
            return borrowRecord;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return borrowRecord;
    }

    public BorrowRecord delete(BorrowRecord borrowRecord){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            session.remove(borrowRecord);
            transaction.commit();
            return borrowRecord;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return borrowRecord;
    }

    public List<BorrowRecord> findAll() {
        Transaction transaction = null;
        List<BorrowRecord> borrowRecords = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            borrowRecords = session.createQuery("from BorrowRecord", BorrowRecord.class).list();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        return borrowRecords;
    }

}