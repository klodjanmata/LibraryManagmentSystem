package Actions;

import Entity.Book;
import Entity.BorrowRecord;
import Entity.Member;
import Repository.BookRepository;
import Repository.BorrowrecordRepository;
import Repository.MemberRepository;
import Util.Helper;

import java.time.LocalDate;
import java.util.List;

public class BorrowRecordActions extends BorrowrecordRepository {

    private BorrowrecordRepository borrowRecordRepository = new BorrowrecordRepository();
    private MemberRepository memberRepository = new MemberRepository();
    private BookRepository bookRepository = new BookRepository();

    public BorrowRecordActions(BorrowrecordRepository borrowrecordRepository) {
    }

    public void addBorrowRecord() {
        System.out.println("Enter Member ID:");
        Long memberId = Helper.readLong();
        Member member = memberRepository.findById(memberId);
        if (member == null) {
            System.out.println("Member not found!");
            return;
        }

        System.out.println("Enter Book ID:");
        Long bookId = Helper.readLong();
        Book book = bookRepository.findById(bookId);
        if (book == null) {
            System.out.println("Book not found!");
            return;
        }

        if (book.getAvailableCopies() <= 0) {
            System.out.println("No copies available for this book!");
            return;
        }

        BorrowRecord record = new BorrowRecord();
        record.setMember(member);
        record.setBook(book);
        record.setBorrowDate(LocalDate.now());
        record.setPenalty(0.0);

        borrowRecordRepository.save(record);

        book.setAvailableCopies(book.getAvailableCopies() - 1);
        bookRepository.update(book);

        System.out.println("Borrow record created successfully.");
    }

    public void returnBook() {
        System.out.println("Enter Borrow Record ID:");
        Long recordId = Helper.readLong();
        BorrowRecord record = borrowRecordRepository.findById(recordId);
        if (record == null) {
            System.out.println("Borrow record not found!");
            return;
        }

        if (record.getReturnDate() != null) {
            System.out.println("This book is already returned.");
            return;
        }

        record.setReturnDate(LocalDate.now());

        long daysBorrowed = java.time.temporal.ChronoUnit.DAYS.between(record.getBorrowDate(), record.getReturnDate());
        if (daysBorrowed > 14) {
            record.setPenalty((daysBorrowed - 14) * 1.0);
        }

        borrowRecordRepository.update(record);

        Book book = record.getBook();
        book.setAvailableCopies(book.getAvailableCopies() + 1);
        bookRepository.update(book);

        System.out.println("Book returned successfully. Penalty: " + record.getPenalty());
    }

    public void printAllBorrowRecords() {
        List<BorrowRecord> records = borrowRecordRepository.findAll();
        if (records.isEmpty()) {
            System.out.println("No borrow records found.");
        } else {
            records.forEach(System.out::println);
        }
    }
}
