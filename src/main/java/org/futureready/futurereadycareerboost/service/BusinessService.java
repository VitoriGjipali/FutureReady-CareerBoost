package org.futureready.futurereadycareerboost.service;

import lombok.RequiredArgsConstructor;
import org.futureready.futurereadycareerboost.entity.*;
import org.futureready.futurereadycareerboost.entity.Job;
import org.futureready.futurereadycareerboost.repository.*;
import org.futureready.futurereadycareerboost.repository.JobRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor

public class BusinessService {

    private final BusinessRepository businessRepository;
    private final JobRepository jobRepository;
    private final MentorRepository mentorRepository;
    private final AppointmentRepository appointmentRepository;
    private final JobApplicatinRepository jobApplicationRepository;
    private final StudentRepository studentRepository;


    // Merr të gjithë bizneset
    public List<Business> getAllBusinesses() {
        return businessRepository.findAll();
    }

    // Merr një biznes sipas ID
    public Business getBusinessById(Long id) {
        return businessRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Business not found"));
    }

    // Shton një biznes të ri
    public Business createBusiness(Business business) {
        return businessRepository.save(business);
    }

    // Përditëson një biznes ekzistues
    public Business updateBusiness(Long id, Business updatedBusiness) {
        Business existing = getBusinessById(id);
        existing.setCompanyName((String) updatedBusiness.getCompanyName());
        existing.setIndustry(updatedBusiness.getIndustry());
        return businessRepository.save(existing);
    }

    // Fshin një biznes
    public void deleteBusiness(Long id) {
        if (businessRepository.existsById(id)) {
            businessRepository.deleteById(id);
        } else {
            throw new RuntimeException("Business not found");
        }
    }

    public void publishJob(Long businessId, String title, String description, LocalDate deadline) {
        Business business = businessRepository.findById(businessId)
                .orElseThrow(() -> new RuntimeException("Business not found"));

        Job job = new Job();
        job.setTitle(title);
        job.setDescription(description);
        job.setDeadline(deadline);
        job.setBusiness(business);

        jobRepository.save(job);
    }

    public void approveApplication(Long applicationId) {
        JobApplication app = jobApplicationRepository.findById(applicationId)
                .orElseThrow(() -> new RuntimeException("Application not found"));
        app.setStatus("APPROVED");
        jobApplicationRepository.save(app);
    }


    public List<Mentor> getAllMentors() {
        return mentorRepository.findAll();
    }

    public List<Appointment> getStudentsByMentor(Long mentorId) {
        return appointmentRepository.findByMentorId(mentorId);
    }

    public void sendApplication(Long businessId, Long studentId, String description) {
        Business business = businessRepository.findById(businessId)
                .orElseThrow(() -> new RuntimeException("Business not found"));
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        JobApplication app = new JobApplication();
        app.setBusiness(business);
        app.setStudent(student);
        app.setDescription(description);
        app.setStatus("SENT");

        jobApplicationRepository.save(app);
    }


}
