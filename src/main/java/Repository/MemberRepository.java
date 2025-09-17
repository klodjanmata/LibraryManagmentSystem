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
            List<Member> member = null;

            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                transaction = session.beginTransaction();

                member = session.createQuery("from MemberRepository", Member.class).list();

                transaction.commit();
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                e.printStackTrace();
            }

            return member;
        }
    }
