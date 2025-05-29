package org.futureready.futurereadycareerboost.repository;

import org.futureready.futurereadycareerboost.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByStudentId(Long studentId);
    List<Appointment> findByMentorId(Long mentorId);

    List<Appointment> findByStudentIdAndMentorId(Long studentId, Long mentorId);
}