package org.futureready.futurereadycareerboost.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity

public class JobApplication {
    @Id
    @GeneratedValue
    private Long id;
    private LocalDate date;

    private String name;
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "business_id")
    private Business business;

    @ManyToOne
    @JoinColumn(name = "job_id")
    private Job job;


    public void setStatus(String pending) {

    }
}
