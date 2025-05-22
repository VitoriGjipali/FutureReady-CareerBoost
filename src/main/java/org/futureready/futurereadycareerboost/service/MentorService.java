package org.futureready.futurereadycareerboost.service;

import org.futureready.futurereadycareerboost.entity.Mentor;
import org.futureready.futurereadycareerboost.repository.MentorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MentorService {

    @Autowired
    private MentorRepository mentorRepository;


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
}
