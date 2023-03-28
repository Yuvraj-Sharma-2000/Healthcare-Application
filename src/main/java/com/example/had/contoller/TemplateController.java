package com.example.had.contoller;


import com.example.had.entity.Doctor;
import com.example.had.request.loginRequestBody;
import com.example.had.service.doctorService;
import com.example.had.service.loginService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/")
@PreAuthorize("permitAll()")
public class TemplateController {
    private final loginService loginService;
    private final doctorService doctorService;

    public TemplateController(loginService loginService, doctorService doctorService) {
        this.loginService = loginService;
        this.doctorService = doctorService;
    }
    @GetMapping("custom-logout")
    public String connectionCheck(){
        return "courses";
    }
    @GetMapping("get/doctors")
    public ResponseEntity<?> getDoctors(){
        List<Doctor> allDoctors = doctorService.getAllDoctors();
        if (allDoctors != null)
            return ResponseEntity.ok(allDoctors);
        return ResponseEntity.noContent().build();
    }
    @PostMapping("login-timestamp")
    public ResponseEntity<?> getLogin(@RequestBody loginRequestBody loginRequestBody) {
        return loginService.getUserByLogin(loginRequestBody.getUsername(), loginRequestBody.getPassword());
    }
}