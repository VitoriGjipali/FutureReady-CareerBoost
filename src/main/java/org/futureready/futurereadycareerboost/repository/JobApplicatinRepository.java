package org.futureready.futurereadycareerboost.repository;

import org.futureready.futurereadycareerboost.entity.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobApplicatinRepository extends JpaRepository<JobApplication, Long> {



}
