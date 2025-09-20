package Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "membership_date")
    private LocalDate membership_date;

    public LocalDate getMembershipDate() {
        return membership_date;
    }

    public String getMembershipDateString() {
        if (membership_date == null) return "N/A";
        return membership_date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
