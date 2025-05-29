package org.futureready.futurereadycareerboost.service;

import org.futureready.futurereadycareerboost.entity.Appointment;
import org.futureready.futurereadycareerboost.entity.Business;
import org.futureready.futurereadycareerboost.entity.Mentor;
import org.futureready.futurereadycareerboost.repository.AppointmentRepository;
import org.futureready.futurereadycareerboost.repository.BusinessRepository;
import org.futureready.futurereadycareerboost.repository.MentorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MentorService {

    @Autowired
    private MentorRepository mentorRepository;
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private BusinessRepository businessRepository;


    public List<Mentor> getAllMentors() {
        return mentorRepository.findAll();
    }

    public Optional<Mentor> getMentorById(Long id) {
        return mentorRepository.findById(id);
    }

    public Mentor createMentor(Mentor mentor) {
        return mentorRepository.save(mentor);
    }

    public Mentor updateMentor(Long id, Mentor updatedMentor) {
        return mentorRepository.findById(id).map(mentor -> {
            mentor.setName(updatedMentor.getName());
            mentor.setField(updatedMentor.getField());
            mentor.setExperience(updatedMentor.getExperience());
            mentor.setAvailableTimes(updatedMentor.getAvailableTimes());
            mentor.setAvailable(updatedMentor.isAvailable());
            return mentorRepository.save(mentor);
        }).orElseThrow(() -> new RuntimeException("Mentor not found"));
    }

    public void deleteMentor(Long id) {
        mentorRepository.deleteById(id);
    }


    public List<Mentor> getMentorsByField(String field) {
        return mentorRepository.findByFieldContainingIgnoreCase(field);
    }

    //Pranon ose refuzon takime
    public void updateAppointmentStatus(Long appointmentId, String newStatus) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));
        appointment.setStatus(newStatus.toUpperCase()); // PENDING, APPROVED, CANCELED
        appointmentRepository.save(appointment);
    }

    //Përditëson profilin e vet
    public Mentor updateMentorProfile(Long id, Mentor updatedMentor) {
        return mentorRepository.findById(id).map(mentor -> {
            mentor.setName(updatedMentor.getName());
            mentor.setField(updatedMentor.getField());
            mentor.setExperience(updatedMentor.getExperience());
            mentor.setAvailableTimes(updatedMentor.getAvailableTimes());
            mentor.setAvailable(updatedMentor.isAvailable());
            return mentorRepository.save(mentor);
        }).orElseThrow(() -> new RuntimeException("Mentor not found"));
    }

    //Ofron listë biznesesh (i shfaqet studentit)
    public List<Business> getAllBusinesses() {
        return businessRepository.findAll();
    }

    // Lista e studentëve që ka ndihmuar mentori
    public List<Appointment> getAppointmentsForMentor(Long mentorId) {
        return appointmentRepository.findByMentorId(mentorId);
    }

    public Mentor updateProfile(Long mentorId, String field, String experience, String timetable) {
        Mentor mentor = mentorRepository.findById(mentorId)
                .orElseThrow(() -> new RuntimeException("Mentor not found"));
        mentor.setField(field);
        mentor.setExperience(experience);
        mentor.setAvailableTimes(timetable);
        return mentorRepository.save(mentor);
    }
}
