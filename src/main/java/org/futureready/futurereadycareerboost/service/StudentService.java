package org.futureready.futurereadycareerboost.service;

import org.futureready.futurereadycareerboost.entity.*;
import org.futureready.futurereadycareerboost.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private MentorRepository mentorRepository;
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private JobApplicatinRepository jobApplicatinRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Optional<Student> getStudentByName(String name) {
        return studentRepository.findByName(name);
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student updateStudent(Long id, Student updatedStudent) {
        return studentRepository.findById(id).map(student -> {
            student.setName(updatedStudent.getName());
            student.setEmail(updatedStudent.getEmail());
            student.setPassword(updatedStudent.getPassword());
            student.setDegree(updatedStudent.getDegree());
            return studentRepository.save(student);
        }).orElse(null);
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

  


    public Student updateProfile(Long studentId, String name, String email, String degree) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        student.setName(name);
        student.setEmail(email);
        student.setDegree(degree);
        return studentRepository.save(student);
    }

    public Appointment createOrCancelAppointment(Long studentId, Long mentorId, String topic, boolean cancel) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        Mentor mentor = mentorRepository.findById(mentorId)
                .orElseThrow(() -> new RuntimeException("Mentor not found"));
        if (cancel) {
            List<Appointment> existing = appointmentRepository.findByStudentIdAndMentorId(studentId, mentorId);
            if (!existing.isEmpty()) {
                appointmentRepository.deleteAll(existing);
            }
            return null;
        }
        Appointment appointment = new Appointment();
        appointment.setStudent(student);
        appointment.setMentor(mentor);
        appointment.setTopic(topic);
        appointment.setStatus("PENDING");
        appointment.setDateTime(LocalDateTime.now());
        return appointmentRepository.save(appointment);
    }
    public String applyToJob(Long studentId, Long jobId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found"));
        if (LocalDate.now().isAfter(job.getDeadline())) {
            return "Job is no longer available.";
        }
        JobApplication application = new JobApplication();
        application.setStudent(student);
        application.setJob(job);
        application.setBusiness(job.getBusiness());
        application.setStatus("APPLIED");
        application.setDescription("Applied by student");
        jobApplicatinRepository.save(application);
        return "Application submitted successfully.";
    }
    }

