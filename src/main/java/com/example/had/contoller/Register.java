package com.example.had.contoller;

import com.example.had.request.DoctorIdBody;
import com.example.had.request.DoctorRegisterRequest;
import com.example.had.request.UserRegisterRequest;
import com.example.had.service.DoctorRegisterService;
import com.example.had.service.UserRegisterService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Controller
@RequestMapping("/register")
public class Register {
    private final DoctorRegisterService doctorRegisterService;
    private final UserRegisterService userRegisterService;

    public Register(DoctorRegisterService doctorRegisterService,
                    UserRegisterService userRegisterService1) {
        this.doctorRegisterService = doctorRegisterService;
        this.userRegisterService = userRegisterService1;
    }
    @GetMapping("/requests")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity requests(){
        return doctorRegisterService.getAllRequests();
    }
    @PostMapping("/doctor")
    @PreAuthorize("permitAll()")
    public ResponseEntity doctorRegister(@NotNull @RequestBody DoctorRegisterRequest doctorRegisterRequest){
        return doctorRegisterService.registerDoctor(doctorRegisterRequest);
    }
//    @PostMapping("/verify/{doctorId}")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    public ResponseEntity verifyDoctor(@NotNull @PathVariable UUID doctorId){
//        return doctorRegisterService.authDoctor(doctorId);
//    }
    @PostMapping("/verify")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity verifyDoctor(@NotNull @RequestBody DoctorIdBody doctorId){
        return doctorRegisterService.authDoctor(doctorId.getDoctorId());
    }
    @PostMapping("/user")
    public ResponseEntity<?> userRegister(@NotNull @RequestBody UserRegisterRequest userRegisterRequest){
        return userRegisterService.registerUser(userRegisterRequest);
    }
}