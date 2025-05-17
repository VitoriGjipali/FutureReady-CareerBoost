package org.futureready.futurereadycareerboost.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String university;

    private String fieldOfStudy;

    private int year;

    private String careerGoal;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
