package org.futureready.futurereadycareerboost.controller;

import lombok.RequiredArgsConstructor;
import org.futureready.futurereadycareerboost.entity.Appointment;
import org.futureready.futurereadycareerboost.service.AppointmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;

    @PostMapping("/create")
    public ResponseEntity<Appointment> createAppointment(
            @RequestParam Long studentId,
            @RequestParam Long mentorId,
            @RequestParam String topic) {
        return ResponseEntity.ok(appointmentService.createAppointment(studentId, mentorId, topic));
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<Appointment>> getAppointmentsForStudent(@PathVariable Long studentId) {
        return ResponseEntity.ok(appointmentService.getAppointmentsForStudent(studentId));
    }

    @GetMapping("/mentor/{mentorId}")
    public ResponseEntity<List<Appointment>> getAppointmentsForMentor(@PathVariable Long mentorId) {
        return ResponseEntity.ok(appointmentService.getAppointmentsForMentor(mentorId));
    }

    @PatchMapping("/{appointmentId}/status")
    public ResponseEntity<Void> updateStatus(
            @PathVariable Long appointmentId,
            @RequestParam String status) {
        appointmentService.updateAppointmentStatus(appointmentId, status);
        return ResponseEntity.ok().build();
    }
}
