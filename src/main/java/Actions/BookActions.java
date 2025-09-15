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

public class BookActions {
    private List<Book> bookList;
    private BookRepository bookRepository;
    private AuthorRepository authorRepository;
    private GenreRepository genreRepository;

    public BookActions() {
        bookList = new ArrayList<>();
    }

    public BookActions(ArrayList<Book> bookList) {
        this.bookList = bookList;
    }

    private List<Author> buildAuthors(String input){
        String[] ids = input.split(",");
        List<Author> authors = new ArrayList<>();
        for (String i : ids){
            Long id = Long.valueOf(i);
            authors.add(authorRepository.read(id));
        }
        return authors;
    }

    private List<Genre> buildGenre(String input){
        String[] ids = input.split(",");
        List<Genre> genres = new ArrayList<>();
        for (String i : ids){
            Long id = Long.valueOf(i);
            genres.add(genreRepository.read(id));
        }
        return genres;
    }

    public void addBook() {
        System.out.println("Add the necessary book information");
        Book book = new Book();
        book.setTitle(Helper.getStringFromUser("Title"));
        Printer.printAuthors(authorRepository.findAll());
        String authorInput = Helper.getStringFromUser("Put in auth ids " +
                "separated by ',' (1, 2, 3,)");
        book.setAuthors(buildAuthors(authorInput));
        Printer.printGenres(genreRepository.findAll());
        String genreInput = Helper.getStringFromUser("Put in auth ids " +
                "separated by ',' (1, 2, 3,)");
        book.setGenres(buildGenre(genreInput));
        book.setPublished_year(Helper.getIntFromUser("Published Year"));
        book.setAvailable_copies(Helper.getIntFromUser("Number Of Available Copies"));
        bookRepository.create(book);
        System.out.println("Book with id: " + book.getId() + " added successfully");
        bookList.add(book);
    }


    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }
}
