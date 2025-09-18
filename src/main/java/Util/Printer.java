package Util;

import Entity.*;

import java.util.List;

public class Printer {

    public static void printBooks(List<Book> bookList){
            System.out.println("######## Full Book list ########");
            System.out.println("ID\tTitle\tAuthors\tGenre\tPublishedDate");
            for (Book book : bookList){
                System.out.println(
                        book.getId() + "\t" +
                                book.getTitle() + "\t" +
                                book.getAuthors() + "\t" +
                                book.getGenres() + "\t" +
                                book.getPublished_year()
                );
            }
        }
    public static void printAuthors(List<Author> authorList){
        System.out.println("######## Full Author list ########");
        System.out.println("ID\tName\tNationality\tBirthDate");
        for (Author author : authorList){
            System.out.println(
                    author.getId() + "\t" +
                            author.getName() + "\t" +
                            author.getNationality() + "\t" +
                            author.getBirthDate()
            );
        }
    }

    public static void printGenres(List<Genre> genreList){
        System.out.println("######## Full Genre list ########");
        System.out.println("ID\tName\tDescription");
        for (Genre genre : genreList){
            System.out.println(
                    genre.getId() + "\t" +
                            genre.getName()+ "\t" +
                            genre.getDescription()
            );
        }
    }

    public static void printMembers(List<Member> memberList){
        System.out.println("######## Full Member list ########");
        System.out.println("ID\tName\tEmail\tPhone\tMembership_Date");
        for (Member member : memberList){
            System.out.println(
                    member.getId() + "\t" +
                            member.getName() + "\t" +
                            member.getEmail() + "\t" +
                            member.getPhone() + "\t"+
                            member.getMembership_date()
            );
        }
    }

    public static void printBorrowRecords(List<BorrowRecord> borrowRecordList){
        System.out.println("######## Full Borrow Records list ########");
        System.out.println("ID\tName\tNationality\tBirthDate");
        for (BorrowRecord borrowRecord : borrowRecordList){
            System.out.println(
                    borrowRecord.getId() + "\t" +
                            borrowRecord.getMember() + "\t" +
                            borrowRecord.getBook() + "\t" +
                            borrowRecord.getBorrow_Date() + "\t" +
                            borrowRecord.getReturn_date() + "\t" +
                            borrowRecord.getPenalty()
            );
        }
    }
    }
