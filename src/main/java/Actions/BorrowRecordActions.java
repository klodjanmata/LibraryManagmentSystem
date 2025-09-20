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

public class BorrowRecordActions {

    private BorrowrecordRepository borrowRecordRepository;
    private MemberRepository memberRepository;
    private BookRepository bookRepository;

    public BorrowRecordActions(BorrowrecordRepository borrowRecordRepository,
                               BookRepository bookRepository,
                               MemberRepository memberRepository) {
        this.borrowRecordRepository = borrowRecordRepository;
        this.bookRepository = bookRepository;
        this.memberRepository = memberRepository;
    }

    // Add borrow record using member name and book title
    public void addBorrowRecord() {
        String memberName = Helper.getStringFromUser("Enter Member Name: ");
        List<Member> members = memberRepository.findByName(memberName);
        if (members.isEmpty()) {
            System.out.println("No member found with name: " + memberName);
            return;
        }
        Member member = members.get(0);

        String bookTitle = Helper.getStringFromUser("Enter Book Title: ");
        List<Book> books = bookRepository.findByTitle(bookTitle, bookRepository);
        if (books.isEmpty()) {
            System.out.println("No book found with title: " + bookTitle);
            return;
        }
        Book book = books.get(0);

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

        System.out.println("Borrow record created successfully for member: " + member.getName());
    }

    // Return book by member name and book title
    public void returnBook() {
        String memberName = Helper.getStringFromUser("Enter Member Name: ");
        List<Member> members = memberRepository.findByName(memberName);
        if (members.isEmpty()) {
            System.out.println("No member found with name: " + memberName);
            return;
        }
        Member member = members.get(0);

        String bookTitle = Helper.getStringFromUser("Enter Book Title: ");
        List<Book> books = bookRepository.findByTitle(bookTitle, bookRepository);
        if (books.isEmpty()) {
            System.out.println("No book found with title: " + bookTitle);
            return;
        }
        Book book = books.get(0);

        List<BorrowRecord> records = borrowRecordRepository.findByMemberName(member.getName());
        BorrowRecord record = records.stream()
                .filter(r -> r.getBook().getTitle().equalsIgnoreCase(book.getTitle()) && r.getReturnDate() == null)
                .findFirst()
                .orElse(null);

        if (record == null) {
            System.out.println("No active borrow record found for this member and book!");
            return;
        }

        record.setReturnDate(LocalDate.now());
        long daysBorrowed = java.time.temporal.ChronoUnit.DAYS.between(record.getBorrowDate(), record.getReturnDate());
        if (daysBorrowed > 14) {
            record.setPenalty((daysBorrowed - 14) * 100.0);
        }

        borrowRecordRepository.update(record);

        book.setAvailableCopies(book.getAvailableCopies() + 1);
        bookRepository.update(book);

        System.out.println("Book returned successfully. Penalty: " + record.getPenalty());
    }

    // Print all borrow records
    public void printAllBorrowRecords() {
        List<BorrowRecord> records = borrowRecordRepository.findAll();
        if (records.isEmpty()) {
            System.out.println("No borrow records found.");
        } else {
            records.forEach(System.out::println);
        }
    }

    public void printBorrowRecordsByMemberName() {
        String memberName = Helper.getStringFromUser("Enter Member Name: ");
        List<BorrowRecord> records = borrowRecordRepository.findByMemberName(memberName);
        if (records.isEmpty()) {
            System.out.println("No borrow records found for member: " + memberName);
        } else {
            records.forEach(System.out::println);
        }
    }
}
