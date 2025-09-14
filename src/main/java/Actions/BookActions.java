package Actions;

import Entity.Author;
import Entity.Book;
import Repository.AuthorRepository;
import Repository.BookRepository;
import Util.Helper;
import Util.Printer;

import java.util.ArrayList;
import java.util.List;

public class BookActions {
    private List<Book> bookList;
    private BookRepository bookRepository;
    private AuthorRepository authorRepository;

    public BookActions() {
        bookList = new ArrayList<>();
    }

    private BookActions(ArrayList<Book> bookList) {
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

    public void addBook() {
        System.out.println("Add the necessary book information");
        Book book = new Book();
        book.setTitle(Helper.getStringFromUser("Title"));
        Printer.printAuthors(authorRepository.findAll());
        String input = Helper.getStringFromUser("Put in auth ids separated by ',' (1, 2, 3,)");
        book.setAuthors(buildAuthors(input));
        book.setNationaity(Helper.getStringFromUser("Nationality"));
        book.setBirthDate(Helper.getLocalDateFromUser("BirthDate"));
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
