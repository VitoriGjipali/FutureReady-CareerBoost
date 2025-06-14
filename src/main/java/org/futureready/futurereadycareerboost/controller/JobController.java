package org.futureready.futurereadycareerboost.controller;

import org.futureready.futurereadycareerboost.entity.Job;
import org.futureready.futurereadycareerboost.service.JobService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

    @RestController
    @RequestMapping("/jobs")
    public class JobController {

        private final JobService jobService;

        public JobController(JobService jobService) {
            this.jobService = jobService;
        }


        @GetMapping
        public List<Job> getAllJobs() {
            return jobService.getAllJobs();
        }


        @GetMapping("/title")
        public ResponseEntity<Job> getJobByTitle(@PathVariable String title) {
            Optional<Job> job = jobService.getJobByTitle(title);
            return job.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
        }


        @PostMapping
        public Job createJob(@RequestBody Job job) {
            return jobService.saveJob(job);
        }


        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteJob(@PathVariable Long id) {
            jobService.deleteJob(id);
            return ResponseEntity.noContent().build();
        }
    }

