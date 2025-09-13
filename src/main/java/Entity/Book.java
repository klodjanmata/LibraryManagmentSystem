package Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "title")
    private String title;

    @OneToMany
    private List<Author> authors;

    @OneToMany
    private List<Genre> genres;

    @Column(name = "published_year")
    private int published_year;

    @Column(name = "available_copies")
    private int available_copies;


}
