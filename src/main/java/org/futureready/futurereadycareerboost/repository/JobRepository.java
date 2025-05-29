package org.futureready.futurereadycareerboost.repository;

import org.futureready.futurereadycareerboost.entity.Business;
import org.futureready.futurereadycareerboost.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {


    Optional<Job> findByTitle(String title);


        // 🏢 Get all jobs by a specific business
        List<Job> findByBusiness(Business business);
}
