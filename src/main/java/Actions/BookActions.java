package Actions;

import Entity.Author;
import Entity.Book;
import Entity.Genre;
import Repository.AuthorRepository;
import Repository.BookRepository;
import Repository.GenreRepository;
import Util.Helper;
import Util.Printer;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BookActions {

    @Getter
    private List<Book> bookList;
    private BookRepository bookRepository;
    private AuthorRepository authorRepository;
    private GenreRepository genreRepository;

    public BookActions(BookRepository bookRepository, AuthorRepository authorRepository, GenreRepository genreRepository, List<Book> bookList) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.genreRepository = genreRepository;
        this.bookList = bookList != null ? bookList : new ArrayList<>();
    }

    private List<Author> buildAuthors(String input) {
        String[] names = input.split(",");
        List<Author> authors = new ArrayList<>();

        for (String name : names) {
            String trimmed = name.trim();
            List<Author> foundAuthors = authorRepository.findByName(trimmed);
            if (foundAuthors != null && !foundAuthors.isEmpty()) {
                if (foundAuthors.size() == 1) {
                    authors.add(foundAuthors.get(0));
                } else {
                    System.out.println("Multiple authors found for '" + trimmed + "':");
                    for (int i = 0; i < foundAuthors.size(); i++) {
                        System.out.println((i + 1) + ". " + foundAuthors.get(i));
                    }
                    int choice = Helper.getIntFromUser("Choose the correct author number: ");
                    authors.add(foundAuthors.get(choice - 1));
                }
            } else {
                System.out.println("Author not found: " + trimmed);
            }
        }
        return authors;
    }

    private List<Genre> buildGenres(String input) {
        String[] names = input.split(",");
        List<Genre> genres = new ArrayList<>();

        for (String name : names) {
            List<Genre> foundGenres = genreRepository.findByName(name.trim());
            if (foundGenres != null && !foundGenres.isEmpty()) {
                genres.add(foundGenres.get(0)); // take first match
            } else {
                System.out.println("Genre not found: " + name.trim());
            }
        }
        return genres;
    }

    public void addBook() {
        System.out.println("\n--- Add New Book ---");

        Book book = new Book();
        book.setTitle(Helper.getStringFromUser("Title"));

        System.out.println("\n--- Existing Authors ---");
        Printer.printAuthors(authorRepository.findAll());
        String authorInput = Helper.getStringFromUser("Enter author NAMES separated by ',' (e.g., John Doe, Jane Smith)");
        book.setAuthors(buildAuthors(authorInput));

        System.out.println("\n--- Existing Genres ---");
        Printer.printGenres(genreRepository.findAll());
        String genreInput = Helper.getStringFromUser("Enter genre NAMES separated by ',' (e.g., novel, drama, thriller)");
        book.setGenres(buildGenres(genreInput));

        book.setPublishedYear(Helper.getIntFromUser("Published Year"));

        int copies;
        do {
            copies = Helper.getIntFromUser("Number of Available Copies");
            if (copies < 0) System.out.println("Number of copies cannot be negative.");
        } while (copies < 0);

        book.setAvailableCopies(copies);

        bookRepository.create(book);
        System.out.println("Book with ID: " + book.getId() + " added successfully");
        bookList.add(book);
    }

    public List<Book> findByTitle(String bookTitle) {
        if (bookTitle == null || bookTitle.isEmpty()) {
            return bookRepository.findAll();
        }
        return bookRepository.findAll().stream()
                .filter(b -> b.getTitle().equalsIgnoreCase(bookTitle))
                .collect(Collectors.toList());
    }

    public List<Book> filterBooks(String title, String genreName) {
        List<Book> allBooks = bookRepository.findAll();
        return allBooks.stream()
                .filter(b -> (title.isEmpty() || b.getTitle().toLowerCase().contains(title.toLowerCase())))
                .filter(b -> (genreName.isEmpty() || b.getGenres().stream()
                        .anyMatch(g -> g.getName().equalsIgnoreCase(genreName))))
                .collect(Collectors.toList());
    }

    public void printAllBooks() {
        List<Book> allBooks = bookRepository.findAll();
        if (allBooks.isEmpty()) {
            System.out.println("No books found.");
        } else {
            allBooks.forEach(System.out::println);
        }
    }

}
