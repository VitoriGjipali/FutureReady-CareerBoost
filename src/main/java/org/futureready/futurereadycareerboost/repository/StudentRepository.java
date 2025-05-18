package org.futureready.futurereadycareerboost.repository;

import org.futureready.futurereadycareerboost.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    // Mund të shtosh metoda custom nëse duhet
}
