package Actions;

import Entity.Book;
import Entity.BorrowRecord;
import Entity.Member;
import Repository.BookRepository;
import Repository.BorrowrecordRepository;
import Repository.MemberRepository;
import Util.Helper;
import Util.Printer;

import java.util.ArrayList;
import java.util.List;

public class BorrowRecordActions {

    private List<BorrowRecord> borrowRecordList;
    private BorrowrecordRepository borrowrecordRepository;
    private BookRepository bookRepository;
    private MemberRepository memberRepository;

    public BorrowRecordActions(List<BorrowRecord> borrowRecordList) {
        this.borrowRecordList = borrowRecordList;
    }

    public BorrowRecordActions(List<BorrowRecord> borrowRecordList, BorrowrecordRepository borrowrecordRepository,
                               BookRepository bookRepository, MemberRepository memberRepository) {
        this.borrowRecordList = borrowRecordList;
        this.borrowrecordRepository = borrowrecordRepository;
        this.bookRepository = bookRepository;
        this.memberRepository = memberRepository;
    }

    private Book buildBooks(String input) {
        Long id = Long.valueOf(input.trim());
        return bookRepository.read(id);
    }

    private Member buildMember(String input) {
        Long id = Long.valueOf(input.trim());
        return memberRepository.read(id);
    }

    public void addBorrowRecord(){
        System.out.println("Add the necessary Borrow Record information");
        BorrowRecord borrowRecord = new BorrowRecord();

        Printer.printMembers(memberRepository.findAll());
        String memberInput = Helper.getStringFromUser("Put member ID: ");
        borrowRecord.setMember(buildMember(memberInput));

        Printer.printBooks(bookRepository.findAll());
        String bookInput = Helper.getStringFromUser("Put book ID: ");
        borrowRecord.setBook(buildBooks(bookInput));

        borrowRecord.setBorrow_Date(Helper.getLocalDateFromUser("Borrow Date"));
        borrowRecord.setReturn_date(Helper.getLocalDateFromUser("Return Date"));
        borrowRecord.setPenalty(Helper.getIntFromUser("Penalty"));

        borrowrecordRepository.create(borrowRecord);
        System.out.println("Borrow Record with id: " + borrowRecord.getId() + "added successfully");
        borrowRecordList.add(borrowRecord);
    }

}
