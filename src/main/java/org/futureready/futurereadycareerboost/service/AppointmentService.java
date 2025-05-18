package org.futureready.futurereadycareerboost.service;

import lombok.RequiredArgsConstructor;
import org.futureready.futurereadycareerboost.entity.Appointment;
import org.futureready.futurereadycareerboost.entity.Mentor;
import org.futureready.futurereadycareerboost.entity.Student;
import org.futureready.futurereadycareerboost.repository.AppointmentRepository;
import org.futureready.futurereadycareerboost.repository.MentorRepository;
import org.futureready.futurereadycareerboost.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final StudentRepository studentRepository;
    private final MentorRepository mentorRepository;

    public Appointment createAppointment(Long studentId, Long mentorId, String topic) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Mentor mentor = mentorRepository.findById(mentorId)
                .orElseThrow(() -> new RuntimeException("Mentor not found"));

        Appointment appointment = new Appointment();
        appointment.setStudent(student);
        appointment.setMentor(mentor);
        appointment.setDateTime(LocalDateTime.now()); // ose kalo si param
        appointment.setTopic(topic);
        appointment.setStatus("PENDING");

        return appointmentRepository.save(appointment);
    }

    public List<Appointment> getAppointmentsForStudent(Long studentId) {
        return appointmentRepository.findByStudentId(studentId);
    }

    public List<Appointment> getAppointmentsForMentor(Long mentorId) {
        return appointmentRepository.findByMentorId(mentorId);
    }

    public void updateStatus(Long appointmentId, String newStatus) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

        appointment.setStatus(newStatus.toUpperCase());
        appointmentRepository.save(appointment);
    }
}

