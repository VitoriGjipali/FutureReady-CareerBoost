package org.futureready.futurereadycareerboost.controller;

import org.futureready.futurereadycareerboost.entity.Mentor;
import org.futureready.futurereadycareerboost.service.MentorService;
import org.futureready.futurereadycareerboost.service.StudentService;
import org.futureready.futurereadycareerboost.entity.Student;
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

        @GetMapping("/student/name")
        public ResponseEntity<Student> getStudentByName(@PathVariable String name) {
            return studentService.getStudentByName(name)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        }

        @PostMapping("/student/create")
        public Student createStudent(@RequestBody Student student) {
            return studentService.createStudent(student);
        }

        @PutMapping("/student/update")
        public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student updatedStudent) {
            Student updated = studentService.updateStudent(id, updatedStudent);
            if (updated != null) {
                return ResponseEntity.ok(updated);
            } else {
                return ResponseEntity.notFound().build();
            }
        }

        @DeleteMapping("/student/delete")
        public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
            studentService.deleteStudent(id);
            return ResponseEntity.noContent().build();
        }

    }

