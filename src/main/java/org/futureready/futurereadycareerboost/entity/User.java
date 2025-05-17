package org.futureready.futurereadycareerboost.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    @Column(unique = true, nullable = false)
    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role; // STUDENT, MENTOR, ADMIN

    // Për lidhje me profile mentorësh (nëse është mentor)
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private MentorProfile mentorProfile;

    // Për lidhje me profile studentësh (nëse është student)
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private StudentProfile studentProfile;
}
