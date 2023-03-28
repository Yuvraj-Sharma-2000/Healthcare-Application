package com.example.had.contoller;

import com.example.had.entity.Doctor;
import com.example.had.entity.User;
import com.example.had.request.doctorProfileBody;
import com.example.had.service.doctorService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/doctor")
@PreAuthorize("hasRole('ROLE_DOCTOR')")
public class DoctorController {
    private final doctorService doctorService;

    public DoctorController(doctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping("/dashboard/get-reg-patients/{doctorId}")
    public ResponseEntity<List<User>> getRegisteredPatients(@PathVariable UUID doctorId){
        List<User> registeredPatients = doctorService.getRegisteredPatients(doctorId);
        if (registeredPatients!=null)
            return ResponseEntity.ok(registeredPatients);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/dashboard/requests/{doctorId}")
    public ResponseEntity<List<User>> getRequests(@PathVariable UUID doctorId){
        List<User> requests = doctorService.getRequests(doctorId);
        return ResponseEntity.ok(requests);
    }
    @GetMapping("/dashboard/request-response/{doctorId}/{userId}")
    public ResponseEntity<String> getOrder(
            @PathVariable UUID doctorId,
            @PathVariable UUID userId
    ) {
        doctorService.getResponse(doctorId, userId);
        return ResponseEntity.ok("Assigned patient successfully");
    }
    @GetMapping("/my-patients/get/{doctorId}/{patientId}")
    public ResponseEntity<User> getPatient(@PathVariable UUID patientId,
                                           @PathVariable UUID doctorId){
        User patient = doctorService.getPatient(doctorId,patientId);
        if (patient==null)
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(patient);
    }
    @GetMapping("/get/profile/{doctorId}")
    public ResponseEntity<Doctor> getProfile(@PathVariable UUID doctorId){
        Doctor profile = doctorService.getProfile(doctorId);
        if (profile==null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(profile);
    }
    @PostMapping("/update/profile/{doctorId}")
    public ResponseEntity<?> updateProfile(@PathVariable UUID doctorId,
                                           @RequestBody doctorProfileBody doctorProfileBody){
        boolean profile = doctorService.updateProfile(doctorId, doctorProfileBody);
        if (profile)
            return ResponseEntity.ok("Updated Successfully");
        return ResponseEntity.unprocessableEntity().body("Not able to update");
    }
}













