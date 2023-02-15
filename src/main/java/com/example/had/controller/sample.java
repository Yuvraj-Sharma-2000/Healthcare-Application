package com.example.had.controller;

import com.example.had.entity.Doctor;
import com.example.had.service.doctorService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class sample {
    @GetMapping("/getDoctors")
    @PreAuthorize("hasAuthority('doctor:write')")
    public List<Doctor> getDoctors(){
        doctorService doctorService = new doctorService();
        return doctorService.getDoctors();
    }
}
