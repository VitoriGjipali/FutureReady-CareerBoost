package org.futureready.futurereadycareerboost.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.futureready.futurereadycareerboost.entity.Appointment;

import java.util.List;
@Getter
@Slf4j
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Mentor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String field;
    private String experience;
    private String timetable;

    @OneToMany(mappedBy = "mentor")
    private List<Appointment> appointments;



}
