package org.futureready.futurereadycareerboost.repository;

import org.futureready.futurereadycareerboost.model.Mentor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MentorRepository extends JpaRepository<Mentor, Long> {
    List<Mentor> findByFieldContainingIgnoreCase(String field);
}
