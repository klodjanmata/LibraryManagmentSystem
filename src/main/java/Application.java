import Actions.*;
import Repository.*;
import Util.HibernateUtil;

public class Application {

    private AuthorActions authorActions;
    private BookActions bookActions;
    private MemberActions memberActions;
    private GenreActions genreActions;
    private BorrowRecordActions borrowRecordActions;

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private MemberRepository memberRepository;
    private GenreRepository genreRepository;
    private BorrowrecordRepository borrowrecordRepository;


    public static void main(String[] args) {
        while (true){
            Menu.Menu();
            if (manageAction(getChoice())){
                return;
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

}




