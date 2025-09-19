package Actions;

import Entity.Author;
import Entity.Book;
import Entity.Genre;
import Repository.AuthorRepository;
import Repository.BookRepository;
import Repository.GenreRepository;
import Util.Helper;
import Util.Printer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BookActions {

    private List<Book> bookList;
    private BookRepository bookRepository;
    private AuthorRepository authorRepository;
    private GenreRepository genreRepository;

    public BookActions(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
        this.bookList = new ArrayList<>();
    }

    public BookActions(BookRepository bookRepository, AuthorRepository authorRepository, GenreRepository genreRepository, List<Book> bookList) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.genreRepository = genreRepository;
        this.bookList = bookList != null ? bookList : new ArrayList<>();
    }

    private List<Author> buildAuthors(String input) {
        String[] ids = input.split(",");
        List<Author> authors = new ArrayList<>();
        for (String i : ids) {
            Long id = Long.valueOf(i.trim());
            Author author = authorRepository.read(id);
            if (author != null) authors.add(author);
        }
        return authors;
    }

    private List<Genre> buildGenres(String input) {
        String[] names = input.split(",");
        List<Genre> genres = new ArrayList<>();
        for (String name : names) {
            List<Genre> foundGenres = genreRepository.findByName(name.trim());
            if (foundGenres != null && !foundGenres.isEmpty()) {
                genres.add(foundGenres.get(0)); // Take the first match
            } else {
                System.out.println("Genre not found: " + name.trim());
            }
        }
        return genres;
    }

    public void addBook() {
        System.out.println("Add the necessary book information");

        Book book = new Book();
        book.setTitle(Helper.getStringFromUser("Title"));


        Printer.printAuthors(authorRepository.findAll());
        String authorInput = Helper.getStringFromUser("Put in author IDs separated by ',' (e.g., 1,2,3)");
        book.setAuthors(buildAuthors(authorInput));


        Printer.printGenres(genreRepository.findAll());
        String genreInput = Helper.getStringFromUser(
                "Put in genre NAMES separated by ',' (e.g., novel, drama, thriller)"
        );
        book.setGenres(buildGenres(genreInput));


        book.setPublishedYear(Helper.getLocalDateFromUser("Published Year"));
        book.setAvailableCopies(Helper.getIntFromUser("Number of Available Copies"));

        bookRepository.create(book);
        System.out.println("Book with ID: " + book.getId() + " added successfully");
        bookList.add(book);
    }


    public void printAllBooks() {
        List<Book> books = bookRepository.findAll();
        if (books.isEmpty()) {
            System.out.println("No books found.");
        } else {
            System.out.println("List of Books:");
            for (Book book : books) {
                System.out.println("ID: " + book.getId() +
                        ", Title: " + book.getTitle() +
                        ", Published Year: " + book.getPublishedYear() +
                        ", Available Copies: " + book.getAvailableCopies() +
                        ", Authors: " + book.getAuthors() +
                        ", Genres: " + book.getGenres());
            }
        }
    }

    public List<Book> filterBooks(String title, String genreName) {
        List<Book> allBooks = bookRepository.findAll();
        return allBooks.stream()
                .filter(b -> (title.isEmpty() || b.getTitle().toLowerCase().contains(title.toLowerCase())))
                .filter(b -> (genreName.isEmpty() || b.getGenres().stream()
                        .anyMatch(g -> g.getName().equalsIgnoreCase(genreName))))
                .collect(Collectors.toList());
    }

    public List<Book> findByTitle(String bookTitle) {
        if (bookTitle == null || bookTitle.isEmpty()) {
            return bookRepository.findAll();
        }
        return bookRepository.findAll().stream()
                .filter(b -> b.getTitle().equalsIgnoreCase(bookTitle))
                .collect(Collectors.toList());
    }
}

