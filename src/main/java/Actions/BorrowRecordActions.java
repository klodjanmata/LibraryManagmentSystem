package Actions;

import Entity.Book;
import Entity.BorrowRecord;
import Entity.Member;
import Repository.BookRepository;
import Repository.BorrowrecordRepository;
import Repository.MemberRepository;

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

    private List<Book> buildBooks(String input){
        String[] ids = input.split(",");
        List<Book> books = new ArrayList<>();
        for (String i : ids){
            Long id = Long.valueOf(i);
            books.add(bookRepository.read(id));
        }
        return books;
    }

    private List<Member> buildMember(String input){
        String[] ids = input.split(",");
        List<Member> members = new ArrayList<>();
        for (String i : ids){
            Long id = Long.valueOf(i);
            members.add(memberRepository.read(id));
        }
        return members;
    }
}
