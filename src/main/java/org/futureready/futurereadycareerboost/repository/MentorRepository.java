package org.futureready.futurereadycareerboost.repository;

import org.futureready.futurereadycareerboost.entity.Mentor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MentorRepository extends JpaRepository<Mentor, Long> {
    List<Mentor> findByFieldContainingIgnoreCase(String field);
}
