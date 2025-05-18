import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private LocalDate deadline;

//    @ManyToOne
//    @JoinColumn(name = "business_id")
//    private Business business;
}
