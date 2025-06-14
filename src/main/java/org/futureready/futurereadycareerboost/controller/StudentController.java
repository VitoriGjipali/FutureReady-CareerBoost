package org.futureready.futurereadycareerboost.controller;

import org.futureready.futurereadycareerboost.entity.Appointment;
import org.futureready.futurereadycareerboost.entity.Student;
import org.futureready.futurereadycareerboost.service.MentorService;
import org.futureready.futurereadycareerboost.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private MentorService mentorService;

    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/name")
    public ResponseEntity<Student> getStudentByName(@PathVariable String name) {
        return studentService.getStudentByName(name)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public Student createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @PutMapping("/update")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student updatedStudent) {
        Student updated = studentService.updateStudent(id, updatedStudent);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }


    @PutMapping("/profile")
    public ResponseEntity<Student> updateProfile(
            @PathVariable Long studentId,
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String degree) {
        return ResponseEntity.ok(studentService.updateProfile(studentId, name, email, degree));
    }

    @PostMapping("/appointments")
    public ResponseEntity<?> createOrCancelAppointment(
            @PathVariable Long studentId,
            @RequestParam Long mentorId,
            @RequestParam String topic,
            @RequestParam(defaultValue = "false") boolean cancel) {
        Appointment result = studentService.createOrCancelAppointment(studentId, mentorId, topic, cancel);
        return ResponseEntity.ok(result != null ? result : "Takimi u anulua me sukses.");
    }

    @PostMapping("/apply")
    public ResponseEntity<String> applyToJob(
            @PathVariable Long studentId,
            @RequestParam Long jobId) {
        return ResponseEntity.ok(studentService.applyToJob(studentId, jobId));
    }
}

