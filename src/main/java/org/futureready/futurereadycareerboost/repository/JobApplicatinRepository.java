package org.futureready.futurereadycareerboost.repository;

import org.futureready.futurereadycareerboost.entity.JobApplication;
import org.futureready.futurereadycareerboost.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobApplicatinRepository extends JpaRepository<JobApplication, Long> {


}
