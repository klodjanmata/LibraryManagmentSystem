package Util;

import Entity.Book;

import java.util.List;

public class Printer {

    public static void printBooks(List<Book> bookList){
            System.out.println("######## Full Book list ########");
            System.out.println("ID\tName\tNationality\tBirthDate");
            for (Book book : bookList){
                System.out.println(
                        book.getId() + "\t" +
                                book.getTitle() + "\t" +
                                book.getAuthors() + "\t" +
                                book.getGenres() + "\t"+
                                book.getPublished_year()
                );
            }
        }
    }
