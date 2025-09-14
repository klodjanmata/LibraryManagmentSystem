package Repository;

import java.util.List;

public class BookRepository {

    public Author create(Author author){
            try(Session session = HibernateUtil.getSessionFactory().openSession()){
                Transaction transaction = session.beginTransaction();
                session.persist(author);
                transaction.commit();
                return author;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return author;
        }

        public Author read(Long id) {
            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                return session.get(Author.class,id);
            }
        }

        public Author update(Author author){
            try(Session session = HibernateUtil.getSessionFactory().openSession()){
                Transaction transaction = session.beginTransaction();
                session.merge(author);
                transaction.commit();
                return author;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return author;
        }

        public Author delete(Author author){
            try(Session session = HibernateUtil.getSessionFactory().openSession()){
                Transaction transaction = session.beginTransaction();
                session.remove(author);
                transaction.commit();
                return author;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return author;
        }

        public List<Author> findAll() {
            Transaction transaction = null;
            List<Author> authors = null;

            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                transaction = session.beginTransaction();

                authors = session.createQuery("from Author", Author.class).list();

                transaction.commit();
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                e.printStackTrace();
            }

            return authors;
        }

    }

}
