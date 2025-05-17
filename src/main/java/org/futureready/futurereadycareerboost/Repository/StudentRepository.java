package org.futureready.futurereadycareerboost.Repository;

import org.futureready.futurereadycareerboost.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public class StudentRepository {
    public interface Studentrepository extends JpaRepository<Student, Long> {
        Optional<Student> findByEmail(String email);
    }
}
