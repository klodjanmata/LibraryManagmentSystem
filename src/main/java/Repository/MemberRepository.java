package Repository;

import Entity.Member;
import Util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class MemberRepository {

        public Member create(Member member){
            try(Session session = HibernateUtil.getSessionFactory().openSession()){
                Transaction transaction = session.beginTransaction();
                session.persist(member);
                transaction.commit();
                return member;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return member;
        }

    public Member saveMember(Member member) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(member);
            transaction.commit();
            return member;
        } catch (Exception e) {
            if (transaction != null && transaction.getStatus().canRollback()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return null;
    }

        public Member read(Long id) {
            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                return session.get(Member.class,id);
            }
        }

        public Member update(Member member){
            try(Session session = HibernateUtil.getSessionFactory().openSession()){
                Transaction transaction = session.beginTransaction();
                session.merge(member);
                transaction.commit();
                return member;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return member;
        }

        public Member delete(Member member){
            try(Session session = HibernateUtil.getSessionFactory().openSession()){
                Transaction transaction = session.beginTransaction();
                session.remove(member);
                transaction.commit();
                return member;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return member;
        }

    public List<Member> findAll() {
        Transaction transaction = null;
        List<Member> members = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            members = session.createQuery("from Member", Member.class).list();

            transaction.commit();  // commit before closing session
        } catch (Exception e) {
            if (transaction != null && transaction.getStatus().canRollback()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        return members;
    }

}
