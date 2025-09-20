import Actions.*;
import Repository.BorrowrecordRepository;
import Repository.GenreRepository;
import Entity.Author;
import Entity.Book;
import Entity.Genre;
import Entity.BorrowRecord;
import Util.Helper;

import java.util.List;

public class Menu {

    public static void showMainMenu() {
        System.out.println("\n ********** Library Management System **********");
        System.out.println("1. Manage Authors");
        System.out.println("2. Manage Books");
        System.out.println("3. Manage Genres");
        System.out.println("4. Manage Members");
        System.out.println("5. Manage Borrow Records");
        System.out.println("6. Filter Menu");
        System.out.println("0. Shut down");
    }

    public static void showAuthorMenu(AuthorActions authorActions) {
        System.out.println("\n--- Author Management ---");
        System.out.println("1. Add Author");
        System.out.println("2. Print All Authors");
        System.out.println("0. Back");

        int choice = Helper.getIntFromUser("Choose option: ");
        switch (choice) {
            case 1:
                System.out.println("\n--- Existing Authors ---");
                authorActions.getAuthorList().forEach(System.out::println);
                authorActions.addAuthor();
                break;
            case 2:
                authorActions.printAllAuthors();
                break;
            case 0:
                System.out.println("Back to Main Menu...");
                break;
            default:
                System.out.println("Invalid choice!");
        }
    }

    public static void showBookMenu(BookActions bookActions) {
        System.out.println("\n--- Book Management ---");
        System.out.println("1. Add Book");
        System.out.println("2. Print All Books");
        System.out.println("0. Back");

        int choice = Helper.getIntFromUser("Choose option: ");
        switch (choice) {
            case 1:
                System.out.println("\n--- Existing Books ---");
                bookActions.getBookList().forEach(System.out::println);
                bookActions.addBook();
                break;
            case 2:
                bookActions.printAllBooks();
                break;
            case 0:
                System.out.println("Back to Main Menu...");
                break;
            default:
                System.out.println("Invalid choice!");
        }
    }

    public static void showGenreMenu(GenreActions genreActions, GenreRepository genreRepo) {
        System.out.println("\n--- Genre Management ---");
        System.out.println("1. Add Genre");
        System.out.println("2. Print All Genres");
        System.out.println("0. Back");

        int choice = Helper.getIntFromUser("Choose option: ");
        switch (choice) {
            case 1:
                System.out.println("\n--- Existing Genres ---");
                genreRepo.findAll().forEach(System.out::println);
                genreActions.addGenre();
                break;
            case 2:
                List<Genre> genres = genreRepo.findAll();
                if (genres.isEmpty()) {
                    System.out.println("No genres found.");
                } else {
                    System.out.println("\n--- All Genres ---");
                    genres.forEach(System.out::println);
                }
                break;
            case 0:
                System.out.println("Back to Main Menu...");
                break;
            default:
                System.out.println("Invalid choice!");
        }
    }

    public static void showMemberMenu(MemberActions memberActions) {
        System.out.println("\n--- Member Management ---");
        System.out.println("1. Add Member");
        System.out.println("2. Print All Members");
        System.out.println("0. Back");

        int choice = Helper.getIntFromUser("Choose option: ");
        switch (choice) {
            case 1:
                System.out.println("\n--- Existing Members ---");
                memberActions.getMemberList().forEach(System.out::println);
                memberActions.addMember();
                break;
            case 2:
                memberActions.printAllMembers();
                break;
            case 0:
                System.out.println("Back to Main Menu...");
                break;
            default:
                System.out.println("Invalid choice!");
        }
    }

    public static void showBorrowRecordMenu(BorrowRecordActions borrowActions, BorrowrecordRepository borrowRepo) {
        System.out.println("\n--- Borrow Record Management ---");
        System.out.println("1. Add Borrow Record ");
        System.out.println("2. Return Book");
        System.out.println("3. Print All Borrow Records");
        System.out.println("4. Search Borrow Records ");
        System.out.println("0. Back");

        int choice = Helper.getIntFromUser("Choose option: ");
        switch (choice) {
            case 1:
                borrowActions.addBorrowRecord();
                break;
            case 2:
                borrowActions.returnBook();
                break;
            case 3:
                borrowActions.printAllBorrowRecords();
                break;
            case 4:
                String memberName = Helper.getStringFromUser("Enter member name: ");
                List<BorrowRecord> records = borrowRepo.findByMemberName(memberName);
                if (records.isEmpty()) {
                    System.out.println("No borrow records found for this member!");
                } else {
                    records.forEach(System.out::println);
                }
                break;
            case 0:
                System.out.println("Back to Main Menu...");
                break;
            default:
                System.out.println("Invalid choice!");
        }
    }

    public static void showFilterMenu(BookActions bookActions, AuthorActions authorActions, GenreRepository genreRepo) {
        System.out.println("\n--- Filter Menu ---");
        System.out.println("1. Filter by Author");
        System.out.println("2. Filter by Book");
        System.out.println("3. Filter by Genre");
        System.out.println("0. Back");

        int choice = Helper.getIntFromUser("Choose option: ");
        switch (choice) {
            case 1:
                String authorName = Helper.getStringFromUser("Enter author name: ");
                List<Author> authors = authorActions.findByName(authorName);
                if (authors.isEmpty()) {
                    System.out.println("No authors found!");
                } else {
                    authors.forEach(System.out::println);
                }
                break;
            case 2:
                String bookTitle = Helper.getStringFromUser("Enter book title: ");
                List<Book> books = bookActions.findByTitle(bookTitle);
                if (books.isEmpty()) {
                    System.out.println("No books found!");
                } else {
                    books.forEach(System.out::println);
                }
                break;
            case 3:
                String genreName = Helper.getStringFromUser("Enter genre: ");
                List<Genre> genres = genreRepo.findByName(genreName);
                if (genres.isEmpty()) {
                    System.out.println("No genres found!");
                } else {
                    genres.forEach(System.out::println);
                }
                break;
            case 0:
                System.out.println("Back to Main Menu...");
                break;
            default:
                System.out.println("Invalid choice!");
        }
    }
}
