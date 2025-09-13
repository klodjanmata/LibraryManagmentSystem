package Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name ="borrowRecord")
public class BorrowRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private Member member;

    @OneToOne
    private Book book;

    @Column(name = "borrow_date")
    private LocalDate borrow_Date;

    @Column(name = "return_date")
    private LocalDate return_date;

    @Column(name = "penalty")
    private int penalty;

}
