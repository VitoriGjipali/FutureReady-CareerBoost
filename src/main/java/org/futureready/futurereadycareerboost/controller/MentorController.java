package org.futureready.futurereadycareerboost.controller;

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
}
