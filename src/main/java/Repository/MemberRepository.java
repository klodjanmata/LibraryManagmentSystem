package Repository;

import Entity.Member;
import Util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class MemberRepository {

    public Member create(Member member) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(member);
            transaction.commit();
            return member;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return null;
        }
    }

    public Member read(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Member.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Member update(Member member) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Member merged = (Member) session.merge(member);
            transaction.commit();
            return merged;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return null;
        }
    }

    public Member delete(Member member) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.remove(member);
            transaction.commit();
            return member;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return null;
        }
    }

    public List<Member> findAll() {
        Transaction transaction = null;
        List<Member> members = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            members = session.createQuery("FROM Member", Member.class).list();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
        return members;
    }

    public void printAllMembers() {
        List<Member> members = findAll();
        if (members == null || members.isEmpty()) {
            System.out.println("No members found.");
            return;
        }

        System.out.println("\n--- All Members ---");
        for (Member m : members) {
            System.out.println("ID: " + m.getId());
            System.out.println("Name: " + m.getName());
            System.out.println("Email: " + m.getEmail());
            System.out.println("Membership Date: " + m.getMembershipDate());
            System.out.println("------------------");
        }
    }
    public Member findById(Long memberId) {
        return read(memberId);
    }
}
