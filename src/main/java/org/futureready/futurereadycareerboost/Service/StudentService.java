package org.futureready.futurereadycareerboost.Service;

import org.futureready.futurereadycareerboost.Repository.StudentRepository;
import org.futureready.futurereadycareerboost.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

    @Service
public class StudentService {

    @Autowired
    private StudentRepository.Studentrepository studentRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student updateStudent(Long id, Student updatedStudent) {
        return studentRepository.findById(id).map(student -> {
            student.setEmri(updatedStudent.getEmri());
            student.setEmail(updatedStudent.getEmail());
            student.setPassword(updatedStudent.getPassword());
            student.setCvLink(updatedStudent.getCvLink());
            student.setInteresa(updatedStudent.getInteresa());
            student.setAvailableForJob(updatedStudent.isAvailableForJob());
            return studentRepository.save(student);
        }).orElse(null);
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
}

