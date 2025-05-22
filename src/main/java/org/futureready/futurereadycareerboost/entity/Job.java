package org.futureready.futurereadycareerboost.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "jobs")
public class Job {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
       // Foreign key to Mentor

    private String title;
    private String description;
    private LocalDate deadline ;

    @ManyToOne
    private Business business ;
}
