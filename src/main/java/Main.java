import Entity.Author;
import Entity.Book;
import Repository.AuthorRepository;
import Util.Helper;
import Util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.engine.spi.SessionDelegatorBaseImpl;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static Main main = new Main();

    private static Main applicationManager = new Main();
    private AuthorRepository authorRepository = new AuthorRepository();

    public static void main(String[] args) {
        while (true){
            Menu.Menu();
            if (manageAction(getChoice())){
                return;
            }
        }
    }

    private static int getChoice(){
        while (true){
            try{
                int choice = Helper.getIntFromUser("Chose");
                return choice;
            }
            catch (Exception e){
                System.out.println("Invalid input! Try again!");
            }
        }
    }

    private static boolean manageAction(int choice) {
        switch (choice) {
            case 1:
                applicationManager.addAuthor();
                break;
            case 2:
                applicationManager.printAllAuthors();
                break;
            case 3:
                applicationManager.addBook();
                break;
            case 4:
                applicationManager.printAllBooks();
                break;
            case 5:
                applicationManager.addBorrowRecord();
                break;
            case 6:
                applicationManager.printAllBorrowRecords();
                break;
            case 7:
                applicationManager.addGenre();
                break;
            case 8:
                applicationManager.printAllGenres();
                break;
            case 9:
                applicationManager.addMember();
                break;
            case 10:
                applicationManager.printAllMembers();
                break;

            case 11:
                Menu.filterMenu();
                int filterChoice = getChoice();
                applicationManager.handleFilterSelection(filterChoice);
                break;
            case 0:
                System.out.println("Shut down");
                applicationManager.shutDown();
                return true;
            default:
                System.out.println("Invalid choice!!!");
        }
        return false;
    }

    private void shutDown() {
        HibernateUtil.shutdown();
    }

    private void handleFilterSelection(int filterChoice) {
    }

    private void printAllMembers() {
    }

    private void addMember() {

    }

    private void printAllGenres() {
    }

    private void addGenre() {
    }

    private void printAllBooks() {
    }

    private void printAllBorrowRecords() {
    }

    private void addBorrowRecord() {
    }

    private void addBook() {
        String title = Helper.getStringFromUser("Enter book title: ");

        printAllAuthors();
        int authorId = Helper.getIntFromUser("Enter author ID: ");

        Author author;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            author = session.get(Author.class, authorId);
            if (author == null) {
                System.out.println("Author not found!");
                return;
            }

            String genre = Helper.getStringFromUser("Enter book genre: ");
            LocalDate publishedDate = Helper.getLocalDateFromUser("Enter published date (yyyy-mm-dd): ");

            Book book = new Book();
            book.setTitle(title);
            List<Author> authors = new ArrayList<>();
            authors.add(author);
            book.setAuthors(authors);
            book.setGenre(genre);
            book.setPublished_year(publishedDate);

            session.save(book);

            System.out.println(" Book added successfully: " + book);

        } catch (Exception e) {
            System.out.println("Error adding book: " + e.getMessage());
        }
    }

    private void printAllAuthors() {
            List<Author> authors = authorRepository.findAll();
            if (authors == null || authors.isEmpty()) {
                System.out.println("No authors found!");
                return;
            }
            for (Author a : authors) {
                System.out.println(a.getId() + ": " + a.getName());
            }
        }

    private void addAuthor() {
        String name = Helper.getStringFromUser("Enter author name: ");
        String nationality = Helper.getStringFromUser("Enter nationality: ");
        LocalDate birthDate = Helper.getLocalDateFromUser("Enter birth date (yyyy-mm-dd): ");

        Author author = new Author();
        author.setName(name);
        author.setNationality(nationality);
        author.setBirthDate(birthDate);

        authorRepository.create(author);

        System.out.println(" Author added successfully: " + author);
    }
}




