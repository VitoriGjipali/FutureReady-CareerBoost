package org.futureready.futurereadycareerboost.controller;

import org.futureready.futurereadycareerboost.Service.StudentService;
import org.futureready.futurereadycareerboost.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
    @RequestMapping("/api/students")
    @CrossOrigin(origins = "*")
    public class StudentController {

        @Autowired
        private StudentService studentService;

        @GetMapping
        public List<Student> getAllStudents() {
            return studentService.getAllStudents();
        }

        @GetMapping("/{id}")
        public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
            return studentService.getStudentById(id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        }

        @PostMapping
        public Student createStudent(@RequestBody Student student) {
            return studentService.createStudent(student);
        }

        @PutMapping("/{id}")
        public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student updatedStudent) {
            Student updated = studentService.updateStudent(id, updatedStudent);
            if (updated != null) {
                return ResponseEntity.ok(updated);
            } else {
                return ResponseEntity.notFound().build();
            }
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
            studentService.deleteStudent(id);
            return ResponseEntity.noContent().build();
        }
    }

