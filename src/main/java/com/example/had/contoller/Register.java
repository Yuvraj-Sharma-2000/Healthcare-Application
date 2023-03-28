package com.example.had.contoller;

import com.example.had.entity.Doctor;
import com.example.had.request.userRegisterRequest;
import com.example.had.service.doctorRegisterService;
import com.example.had.request.doctorRegisterRequest;
import com.example.had.service.userRegisterService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@Controller
@RequestMapping("/register")
@CrossOrigin(origins = "*")
public class Register {
    private final doctorRegisterService doctorRegisterService;
    private final userRegisterService userRegisterService;

    public Register(doctorRegisterService doctorRegisterService,
                    userRegisterService userRegisterService1) {
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
    public ResponseEntity doctorRegister(@NotNull @RequestBody doctorRegisterRequest doctorRegisterRequest){
        return doctorRegisterService.registerDoctor(doctorRegisterRequest);
    }
    @PostMapping("/verify")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity verifyDoctor(@NotNull @RequestBody doctorRegisterRequest doctorRegisterRequest){
//      --------  TO BE FILLED BY FRONTEND -----------
        return doctorRegisterService.authDoctor(doctorRegisterRequest);
    }
    @PostMapping("/user")
    public ResponseEntity userRegister(@NotNull @RequestBody userRegisterRequest userRegisterRequest){
        return userRegisterService.registerUser(userRegisterRequest);
    }
}