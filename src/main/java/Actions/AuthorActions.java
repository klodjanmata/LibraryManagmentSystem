package Actions;

import Entity.Author;
import Repository.AuthorRepository;
import Util.Helper;

import java.util.ArrayList;
import java.util.List;

public class AuthorActions {

    private List<Author> authorList;
    private AuthorRepository authorRepository;

    public AuthorActions() {
        authorList = new ArrayList<>();
    }

    private AuthorActions(ArrayList<Author> authorList) {
        this.authorList = authorList;
    }

    public void addAuthor() {
        System.out.println("Add the necessary author information");
        Author author = new Author();
        author.setName(Helper.getStringFromUser("Name"));
        author.setNationaity(Helper.getStringFromUser("Nationality"));
        author.setBirthDate(Helper.getLocalDateFromUser("BirthDate"));
        authorRepository.create(author);
        System.out.println("Author with id: " + author.getId() + " added successfully");
        authorList.add(author);
    }

    public void printAllAuthor() {
        System.out.println("######## Full Clients list ########");
        System.out.println("ID\tName\tNationality\tBirthDate");
        for (Author author : authorList){
            System.out.println(
                    author.getId() + "\t" +
                            author.getName() + "\t" +
                            author.getNationaity() + "\t" +
                            author.getBirthDate());
        }
    }

    public List<Author> getAuthorList() {
        return authorList;
    }

    public void setAuthorList(List<Author> authorList) {
        this.authorList = authorList;
    }
}

