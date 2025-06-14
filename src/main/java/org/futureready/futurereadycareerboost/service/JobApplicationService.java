package org.futureready.futurereadycareerboost.service;

import org.futureready.futurereadycareerboost.entity.Business;
import org.futureready.futurereadycareerboost.entity.Job;
import org.futureready.futurereadycareerboost.entity.JobApplication;
import org.futureready.futurereadycareerboost.entity.Student;
import org.futureready.futurereadycareerboost.repository.JobApplicatinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class JobApplicationService {

    @Autowired
    private JobApplicatinRepository repository;

    public JobApplication apply(Student student, Job job, Business business, String name) {
        JobApplication app = new JobApplication();
        app.setStudent(student);
        app.setJob(job);
        app.setBusiness(business);
        app.setName(name);
        app.setDate(LocalDate.now());
        app.setStatus("PENDING");
        return repository.save(app);
    }

    public JobApplication acceptApplication(Long id) {
        JobApplication app = repository.findById(id).orElseThrow();
        app.setStatus("ACCEPTED");
        return repository.save(app);
    }

    public JobApplication rejectApplication(Long id) {
        JobApplication app = repository.findById(id).orElseThrow();
        app.setStatus("REJECTED");
        return repository.save(app);
    }


}

