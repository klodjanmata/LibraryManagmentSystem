import Actions.*;
import Repository.*;
import Util.Helper;
import Util.HibernateUtil;

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
    private BorrowrecordRepository borrowRecordRepository;

    public Application() {
        authorRepository = new AuthorRepository();
        bookRepository = new BookRepository();
        memberRepository = new MemberRepository();
        genreRepository = new GenreRepository();
        borrowRecordRepository = new BorrowrecordRepository();

        authorActions = new AuthorActions(authorRepository);
        bookActions = new BookActions(bookRepository, authorRepository, genreRepository, new ArrayList<>());
        memberActions = new MemberActions(memberRepository);
        genreActions = new GenreActions(genreRepository);
        borrowRecordActions = new BorrowRecordActions(borrowRecordRepository, bookRepository, memberRepository);
    }

    public static void main(String[] args) {
        Application app = new Application();

        while (true) {
            Menu.showMainMenu();
            int choice = Helper.getIntFromUser("Number: ");
            if (app.manageAction(choice)) break;
        }
    }

    private boolean manageAction(int choice) {
        switch (choice) {
            case 1:
                String authorName = Helper.getStringFromUser("Enter author name to search (leave empty for all): ");
                if (authorName.isEmpty()) authorRepository.findAll().forEach(System.out::println);
                else authorActions.findByName(authorName).forEach(System.out::println);
                Menu.showAuthorMenu(authorActions);
                break;

            case 2:
                String bookTitle = Helper.getStringFromUser("Enter book title to search (leave empty for all): ");
                if (bookTitle.isEmpty()) bookRepository.findAll().forEach(System.out::println);
                else bookActions.findByTitle(bookTitle).forEach(System.out::println);
                Menu.showBookMenu(bookActions);
                break;

            case 3:
                String genreName = Helper.getStringFromUser("Enter genre name to search (leave empty for all): ");
                if (genreName.isEmpty()) genreRepository.findAll().forEach(System.out::println);
                else genreRepository.findByName(genreName).forEach(System.out::println);
                Menu.showGenreMenu(genreActions, genreRepository);
                break;

            case 4:
                String memberName = Helper.getStringFromUser("Enter member name to search (leave empty for all): ");
                if (memberName.isEmpty()) memberRepository.findAll().forEach(System.out::println);
                else memberActions.findByName(memberName).forEach(System.out::println);
                Menu.showMemberMenu(memberActions);
                break;

            case 5:

                Menu.showBorrowRecordMenu(borrowRecordActions, borrowRecordRepository);
                break;

            case 6:
                Menu.showFilterMenu(bookActions, authorActions, genreRepository);
                break;

            case 0:
                System.out.println("System Shut Down");
                shutDown();
                return true;

            default:
                System.out.println("Invalid choice!");
        }
        return false;
    }

    private void shutDown() {
        HibernateUtil.shutdown();
    }
}
