package org.futureready.futurereadycareerboost.controller;

import org.futureready.futurereadycareerboost.entity.Appointment;
import org.futureready.futurereadycareerboost.entity.Business;
import org.futureready.futurereadycareerboost.entity.Mentor;
import org.futureready.futurereadycareerboost.service.MentorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/mentors")
public class MentorController {

    @Autowired
    private MentorService mentorService;

    @GetMapping
    public ResponseEntity<List<Mentor>> getAllMentors() {
        return ResponseEntity.ok(mentorService.getAllMentors());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mentor> getMentorById(@PathVariable Long id) {
        Optional<Mentor> mentor = mentorService.getMentorById(id);
        return mentor.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Mentor> createMentor(@RequestBody Mentor mentor) {
        return ResponseEntity.ok(mentorService.createMentor(mentor));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Mentor> updateMentor(@PathVariable Long id, @RequestBody Mentor updatedMentor) {
        try {
            return ResponseEntity.ok(mentorService.updateMentor(id, updatedMentor));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMentor(@PathVariable Long id) {
        mentorService.deleteMentor(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<Mentor>> getMentorsByField(@RequestParam String field) {
        return ResponseEntity.ok(mentorService.getMentorsByField(field));
    }

    //Pranon ose refuzon takim

    @PatchMapping("/appointments/{appointmentId}/status")
    public ResponseEntity<Void> updateAppointmentStatus(
            @PathVariable Long appointmentId,
            @RequestParam String status) {
        mentorService.updateAppointmentStatus(appointmentId, status);
        return ResponseEntity.ok().build();
    }

    //Përditëson profilin e vet

    @PutMapping("/{mentorId}/profile")
    public ResponseEntity<Mentor> updateProfile(
            @PathVariable Long mentorId,
            @RequestParam String field,
            @RequestParam String experience,
            @RequestParam String timetable) {
        return ResponseEntity.ok(
                mentorService.updateProfile(mentorId, field, experience, timetable));
    }

    //Lista e bizneseve që shfaqen për studentin
    @GetMapping("/businesses")
    public ResponseEntity<List<Business>> getAllBusinesses() {
        return ResponseEntity.ok(mentorService.getAllBusinesses());
    }

    //Lista e studentëve që ka ndihmuar mentori
    @GetMapping("/{mentorId}/students")
    public ResponseEntity<List<Appointment>> getAppointmentsForMentor(
            @PathVariable Long mentorId) {
        return ResponseEntity.ok(mentorService.getAppointmentsForMentor(mentorId));
    }



}
