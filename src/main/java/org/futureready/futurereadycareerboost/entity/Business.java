package org.futureready.futurereadycareerboost.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
public class Business {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String companyName;

    @Column(unique = true, nullable = false)
    private String email;

    private String password;

    private String industry;


    // Lidhje me entitetin Job
    @OneToMany(mappedBy = "business", cascade = CascadeType.ALL)
    private List<Job> jobPosted;

//    // Lidhje me JobApplication
//    @OneToMany(mappedBy = "business", cascade = CascadeType.ALL)
//    private List<JobApplication> applicationsSentToStudents;
}

