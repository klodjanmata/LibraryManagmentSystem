import Actions.AuthorActions;
import Actions.BookActions;
import Actions.BorrowRecordActions;
import Repository.GenreRepository;
import Entity.Author;
import Entity.Book;
import Entity.Genre;
import Util.Helper;

import java.util.List;

public class Menu {

    public static void showMainMenu() {
        System.out.println("\n");
        System.out.println(" ********** Welcome to Library Management System **********");
        System.out.println("Choose your action:");
        System.out.println("1. Add Author");
        System.out.println("2. Print All Authors");
        System.out.println("3. Add Book");
        System.out.println("4. Print All Books");
        System.out.println("5. Add Genre");
        System.out.println("6. Print All Genres");
        System.out.println("7. Add Member");
        System.out.println("8. Print All Members");
        System.out.println("9. Add Borrow Record");
        System.out.println("10. Return Book");
        System.out.println("11. Print All Borrow Records");
        System.out.println("12. Filter Menu");
        System.out.println("0. Shut down");
    }

    public static void showFilterMenu(BookActions bookActions, AuthorActions authorActions, GenreRepository genreRepo) {
        System.out.println("\n--- Filter Menu ---");
        System.out.println("1. Filter by Author");
        System.out.println("2. Filter by Book");
        System.out.println("3. Filter by Genre");
        System.out.println("0. Back to Main Menu");

        int choice = Helper.getIntFromUser("Choose filter option: ");

        switch (choice) {
            case 1:
                String authorName = Helper.getStringFromUser("Enter author name: ");
                List<Author> authors = authorActions.findByName(authorName);
                if (authors.isEmpty()) {
                    System.out.println("No authors found!");
                } else {
                    for (Author a : authors) {
                        System.out.println(a);
                    }
                }
                break;

            case 2:
                String bookTitle = Helper.getStringFromUser("Enter book title: ");
                List<Book> books = bookActions.findByTitle(bookTitle);
                if (books.isEmpty()) {
                    System.out.println("No books found!");
                } else {
                    for (Book b : books) {
                        System.out.println(b);
                    }
                }
                break;

            case 3:
                String genreName = Helper.getStringFromUser("Enter genre: ");
                List<Genre> genres = genreRepo.findByName(genreName);
                if (genres.isEmpty()) {
                    System.out.println("No genres found!");
                } else {
                    for (Genre g : genres) {
                        System.out.println(g);
                    }
                }
                break;

            case 0:
                System.out.println("Returning to Main Menu...");
                break;

            default:
                System.out.println("Invalid filter choice!");
        }
    }
}
