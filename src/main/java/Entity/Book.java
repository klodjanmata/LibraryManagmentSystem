package Entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "book")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "Title")
    private String title;

    @Column(name = "published_year")
    private LocalDate published_year;

    @Column(name = "available_copies")
    private int available_copies;

    @OneToMany
    private List<Author> authors;

    @OneToMany
    private List<Genre> genres;

}
