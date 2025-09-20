package Actions;

import Entity.Book;
import Entity.BorrowRecord;
import Entity.Member;
import Repository.BookRepository;
import Repository.BorrowrecordRepository;
import Repository.MemberRepository;
import Util.Helper;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class BorrowRecordActions {

    private final BorrowrecordRepository borrowRecordRepository;
    private final MemberRepository memberRepository;
    private final BookRepository bookRepository;

    public BorrowRecordActions(BorrowrecordRepository borrowRecordRepository,
                               BookRepository bookRepository,
                               MemberRepository memberRepository) {
        this.borrowRecordRepository = borrowRecordRepository;
        this.bookRepository = bookRepository;
        this.memberRepository = memberRepository;
    }

    public void addBorrowRecord() {
        String memberName = Helper.getStringFromUser("Enter Member Name");
        List<Member> members = memberRepository.findByName(memberName);
        if (members.isEmpty()) {
            System.out.println("No member found with name: " + memberName);
            return;
        }
        Member member = members.get(0);

        String bookTitle = Helper.getStringFromUser("Enter Book Title");
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

        LocalDate borrowDate = Helper.getDateFromUser("Enter Borrow Date");
        LocalDate dueDate = Helper.getDateFromUser("Enter Due Date");

        BorrowRecord record = new BorrowRecord();
        record.setMember(member);
        record.setBook(book);
        record.setBorrowDate(borrowDate);
        record.setDueDate(dueDate);
        record.setReturnDate(null);
        record.setPenalty(0.0);

        borrowRecordRepository.save(record);

        book.setAvailableCopies(book.getAvailableCopies() - 1);
        bookRepository.update(book);

        System.out.println("Borrow record created successfully!");
    }

    public void returnBook() {
        String memberName = Helper.getStringFromUser("Enter Member Name");
        List<Member> members = memberRepository.findByName(memberName);
        if (members.isEmpty()) {
            System.out.println("No member found with name: " + memberName);
            return;
        }
        Member member = members.get(0);

        String bookTitle = Helper.getStringFromUser("Enter Book Title");
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

        if (record.getDueDate() != null) {
            System.out.println("The due date was: " + record.getDueDate().format(Helper.DATE_FORMATTER));
        } else {
            System.out.println("No due date was set for this borrow record.");
        }

        LocalDate returnDate = Helper.getDateFromUser("Enter Return Date");
        record.setReturnDate(returnDate);

        if (record.getDueDate() != null) {
            long daysLate = ChronoUnit.DAYS.between(record.getDueDate(), returnDate);
            record.setPenalty(daysLate > 0 ? daysLate * 100.0 : 0.0);
        } else {
            record.setPenalty(0.0);
        }

        borrowRecordRepository.update(record);

        book.setAvailableCopies(book.getAvailableCopies() + 1);
        bookRepository.update(book);

        System.out.println("Book returned successfully. Penalty: " + record.getPenalty());
    }

    public void printAllBorrowRecords() {
        List<BorrowRecord> records = borrowRecordRepository.findAll();
        if (records.isEmpty()) {
            System.out.println("No borrow records found.");
        } else {
            System.out.println("\n--- All Borrow Records ---");
            records.forEach(System.out::println);
        }
    }

}
