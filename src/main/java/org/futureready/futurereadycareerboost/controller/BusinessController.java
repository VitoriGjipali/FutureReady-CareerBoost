package org.futureready.futurereadycareerboost.controller;

import lombok.RequiredArgsConstructor;
import org.futureready.futurereadycareerboost.entity.Appointment;
import org.futureready.futurereadycareerboost.entity.Business;
import org.futureready.futurereadycareerboost.entity.Mentor;
import org.futureready.futurereadycareerboost.repository.BusinessRepository;
import org.futureready.futurereadycareerboost.service.BusinessService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/business")
@RequiredArgsConstructor
public class BusinessController {

    private final BusinessRepository businessRepository;
    private final BusinessService businessService;


    // Merr të gjithë bizneset
    @GetMapping
    public ResponseEntity<List<Business>> getAllBusinesses() {
        return ResponseEntity.ok(businessRepository.findAll());
    }

    // Merr një biznes sipas ID
    @GetMapping("/{id}")
    public ResponseEntity<Business> getBusinessById(@PathVariable Long id) {
        return businessRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Shto një biznes të ri
    @PostMapping
    public ResponseEntity<Business> createBusiness(@RequestBody Business business) {
        return ResponseEntity.ok(businessRepository.save(business));
    }

    // Përditëso informacionet bazë të një biznesi (p.sh. industry, companyName)
    @PutMapping("/{id}")
    public ResponseEntity<Business> updateBusiness(
            @PathVariable Long id,
            @RequestBody Business updatedBusiness) {
        return businessRepository.findById(id)
                .map(existing -> {
                    existing.setCompanyName((String) updatedBusiness.getCompanyName());
                    existing.setIndustry(updatedBusiness.getIndustry());
                    return ResponseEntity.ok(businessRepository.save(existing));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Fshi një biznes
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBusiness(@PathVariable Long id) {
        if (businessRepository.existsById(id)) {
            businessRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    //Publikon punë
    @PostMapping("/{businessId}/publish-job")
    public ResponseEntity<Void> publishJob(
            @PathVariable Long businessId,
            @RequestParam String title,
            @RequestParam String description,
            @RequestParam String deadline // yyyy-MM-dd
    ) {
        businessService.publishJob(businessId, title, description, LocalDate.parse(deadline));
        return ResponseEntity.ok().build();
    }


    //Pranon aplikim
    @PatchMapping("/applications/{applicationId}/approve")
    public ResponseEntity<Void> approveApplication(@PathVariable Long applicationId) {
        businessService.approveApplication(applicationId);
        return ResponseEntity.ok().build();
    }

    //Merr të gjithë mentorët
    @GetMapping("/mentors")
    public ResponseEntity<List<Mentor>> getAllMentors() {
        return ResponseEntity.ok(businessService.getAllMentors());
    }

    //Merr studentët për një mentor
    @GetMapping("/mentors/{mentorId}/students")
    public ResponseEntity<List<Appointment>> getStudentsByMentor(@PathVariable Long mentorId) {
        return ResponseEntity.ok(businessService.getStudentsByMentor(mentorId));
    }

    //Dërgon aplikim te student
    @PostMapping("/{businessId}/send-application")
    public ResponseEntity<Void> sendApplicationToStudent(
            @PathVariable Long businessId,
            @RequestParam Long studentId,
            @RequestParam String description) {
        businessService.sendApplication(businessId, studentId, description);
        return ResponseEntity.ok().build();
    }
}
