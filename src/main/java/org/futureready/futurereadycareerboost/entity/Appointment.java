package org.futureready.futurereadycareerboost.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime appointmentDate;

    private String topic;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private User student; // student që ka rezervuar

    @ManyToOne
    @JoinColumn(name = "mentor_id")
    private MentorProfile mentor;

    @Enumerated(EnumType.STRING)
    private AppointmentStatus status;
}

