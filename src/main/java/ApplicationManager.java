import Actions.*;
import Repository.*;

public class ApplicationManager {

    private AuthorActions authorActions;
    private BookActions bookActions;
    private MemberActions memberActions;
    private GenreActions genreActions;
    private BorrowRecordActions borrowRecordActions;

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private MemberRepository memberRepository;
    private GenreRepository genreRepository;
    private BorrowrecordRepository borrowrecordRepository;

    public ApplicationManager(){

        authorRepository = new AuthorRepository();
        bookRepository = new BookRepository();
        memberRepository = new MemberRepository();
        genreRepository = new GenreRepository();
        borrowrecordRepository = new BorrowrecordRepository();

        authorActions = new AuthorActions(authorRepository);
        bookActions = new BookActions(bookRepository, authorRepository, genreRepository);
        memberActions = new MemberActions(memberRepository);
        genreActions = new GenreActions(genreRepository);
        borrowRecordActions = new BorrowRecordActions(borrowrecordRepository, bookRepository, memberRepository);

    }
}
