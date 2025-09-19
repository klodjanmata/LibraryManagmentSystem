import Actions.*;
import Repository.*;
import Util.Helper;
import Util.HibernateUtil;
import Util.Printer;

import java.util.ArrayList;

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

    public Application() {

        authorRepository = new AuthorRepository();
        bookRepository = new BookRepository();
        memberRepository = new MemberRepository();
        genreRepository = new GenreRepository();
        borrowrecordRepository = new BorrowrecordRepository();


        authorActions = new AuthorActions(authorRepository);
        bookActions = new BookActions(bookRepository, authorRepository, genreRepository, new ArrayList<>());
        memberActions = new MemberActions(memberRepository);
        genreActions = new GenreActions(genreRepository);
        borrowRecordActions = new BorrowRecordActions(borrowrecordRepository);

    }

    public static void main(String[] args) {
        Application app = new Application();

        while (true) {
            Menu.Menu();
            int choice = Helper.getIntFromUser("Number");
            if (app.manageAction(choice)) {
                break;
            }
        }
    }

    private boolean manageAction(int choice) {
        switch (choice) {
            case 1:
                authorActions.addAuthor();
                break;
            case 2:
                Printer.printAuthors(authorRepository.findAll());
                break;
            case 3:
                bookActions.addBook();
                break;
            case 4:
                Printer.printBooks(bookRepository.findAll());
                break;
            case 5:
                genreActions.addGenre();
                break;
            case 6:
                Printer.printGenres(genreRepository.findAll());
                break;
            case 7:
                memberActions.addMember();
                break;
            case 8:
                Printer.printMembers(memberRepository.findAll());
                break;
            case 9:
                borrowRecordActions.addBorrowRecord();
                break;
            case 10:
                Printer.printBorrowRecords(borrowrecordRepository.findAll());
                break;
            case 11:
                Menu.filterMenu(bookActions, authorActions, genreRepository);
                break;
            case 0:
                System.out.println("Shut down");
                shutDown();
                return true;
            default:
                System.out.println("Invalid choice!!!");
        }
        return false;
    }

    private void shutDown() {
        HibernateUtil.shutdown();
    }
}






