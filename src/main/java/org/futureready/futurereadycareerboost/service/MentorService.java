package org.futureready.futurereadycareerboost.service;

import org.futureready.futurereadycareerboost.model.Mentor;
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
        return mentorRepository.findById(id).map(m -> {
            m.setEmri(updatedMentor.getEmri());
            m.setFusha(updatedMentor.getFusha());
            m.setEksperienca(updatedMentor.getEksperienca());
            m.setOraretELira(updatedMentor.getOraretELira());
            m.setAvailable(updatedMentor.isAvailable());
            return mentorRepository.save(m);
        }).orElseThrow(() -> new RuntimeException("Mentori nuk u gjet"));
    }

    public void deleteMentor(Long id) {
        mentorRepository.deleteById(id);
    }

    public List<Mentor> getMentorsByFusha(String fusha) {
        return mentorRepository.findByFusha(fusha);
    }
}
