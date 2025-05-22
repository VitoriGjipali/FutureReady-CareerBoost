package org.futureready.futurereadycareerboost.controller;

import org.futureready.futurereadycareerboost.entity.Business;
import org.futureready.futurereadycareerboost.entity.Job;
import org.futureready.futurereadycareerboost.entity.JobApplication;
import org.futureready.futurereadycareerboost.entity.Student;
import org.futureready.futurereadycareerboost.repository.BusinessRepository;
import org.futureready.futurereadycareerboost.repository.JobRepository;
import org.futureready.futurereadycareerboost.repository.StudentRepository;
import org.futureready.futurereadycareerboost.service.JobApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/applications")
public class JobApplicationController {

    @Autowired
    private JobApplicationService service;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private BusinessRepository businessRepository;

    @PostMapping("/apply")
    public ResponseEntity<JobApplication> applyForJob(
            @RequestParam Long studentId,
            @RequestParam Long jobId,
            @RequestParam Long businessId,
            @RequestParam String name) {

        Student student = studentRepository.findById(studentId).orElseThrow();
        Job job = jobRepository.findById(jobId).orElseThrow();
        Business business = businessRepository.findById(businessId).orElseThrow();

        JobApplication app = service.apply(student,job, business, name);
        return ResponseEntity.ok(app);
    }

    @PutMapping("/accept/{id}")
    public ResponseEntity<JobApplication> acceptApplication(@PathVariable Long id) {
        return ResponseEntity.ok(service.acceptApplication(id));
    }

    @PutMapping("/reject/{id}")
    public ResponseEntity<JobApplication> rejectApplication(@PathVariable Long id) {
        return ResponseEntity.ok(service.rejectApplication(id));
    }


}


