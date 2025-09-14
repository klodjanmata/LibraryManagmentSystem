package Repository;

import Util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class MemberRepository {
    public class MemberRepositoryRepository {

        public MemberRepository create(MemberRepository memberRepository){
            try(Session session = HibernateUtil.getSessionFactory().openSession()){
                Transaction transaction = session.beginTransaction();
                session.persist(memberRepository);
                transaction.commit();
                return memberRepository;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return memberRepository;
        }

        public MemberRepository read(Long id) {
            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                return session.get(MemberRepository.class,id);
            }
        }

        public MemberRepository update(MemberRepository memberRepository){
            try(Session session = HibernateUtil.getSessionFactory().openSession()){
                Transaction transaction = session.beginTransaction();
                session.merge(memberRepository);
                transaction.commit();
                return memberRepository;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return memberRepository;
        }

        public MemberRepository delete(MemberRepository memberRepository){
            try(Session session = HibernateUtil.getSessionFactory().openSession()){
                Transaction transaction = session.beginTransaction();
                session.remove(memberRepository);
                transaction.commit();
                return memberRepository;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return memberRepository;
        }

        public List<MemberRepository> findAll() {
            Transaction transaction = null;
            List<MemberRepository> memberRepositorys = null;

            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                transaction = session.beginTransaction();

                memberRepositorys = session.createQuery("from MemberRepository", MemberRepository.class).list();

                transaction.commit();
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                e.printStackTrace();
            }

            return memberRepositorys;
        }
    }

}
