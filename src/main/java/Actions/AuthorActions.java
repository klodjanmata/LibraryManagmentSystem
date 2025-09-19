package Actions;

import Entity.Author;
import Repository.AuthorRepository;
import Util.Helper;
import Util.HibernateUtil;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class AuthorActions {

    private List<Author> authorList;
    private AuthorRepository authorRepository;

    public AuthorActions() {
        authorList = new ArrayList<>();
    }

    public AuthorActions(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
        this.authorList = new ArrayList<>();
    }


    public void addAuthor() {
        System.out.println("Add the necessary author information");
        Author author = new Author();
        author.setName(Helper.getStringFromUser("Name"));
        author.setNationality(Helper.getStringFromUser("Nationality"));
        author.setBirthDate(Helper.getLocalDateFromUser("BirthDate"));
        authorRepository.create(author);
        System.out.println("Author with id: " + author.getId() + " added successfully");
        authorList.add(author);
    }

    public List<Author> getAuthorList() {
        return authorList;
    }

    public List<Author> findByName(String name) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                            "FROM Author a WHERE lower(a.name) LIKE :name", Author.class)
                    .setParameter("name", "%" + name.toLowerCase() + "%")
                    .list();
        }
    }
    }

