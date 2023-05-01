package com.example.had.contoller;


import com.example.had.entity.Doctor;
import com.example.had.entity.PersonalArticle;
import com.example.had.entity.PrepopulatedArticle;
import com.example.had.request.DeviceTokenBody;
import com.example.had.request.LoginRequestBody;
import com.example.had.request.UserIdBody;
import com.example.had.service.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/")
public class TemplateController {
    private final LoginService loginService;
    private final DoctorService doctorService;
    private final PrepopulatedArticleService prepopulatedArticleService;
    private final PersonalizedArticleService personalizedArticleService;
    private final EmailService emailService;
    private final DeviceTokenService deviceTokenService;
    public TemplateController(LoginService loginService,
                              DoctorService doctorService,
                              PrepopulatedArticleService prepopulatedArticleService,
                              PersonalizedArticleService personalizedArticleService,
                              EmailService emailService,
                              DeviceTokenService deviceTokenService) {
        this.loginService = loginService;
        this.doctorService = doctorService;
        this.prepopulatedArticleService = prepopulatedArticleService;
        this.personalizedArticleService = personalizedArticleService;
        this.emailService = emailService;
        this.deviceTokenService = deviceTokenService;
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
    public ResponseEntity<?> getLogin(@RequestBody LoginRequestBody loginRequestBody) {
        return loginService.getUserByLogin(loginRequestBody.getUsername(), loginRequestBody.getPassword());
    }
    @GetMapping("preData")
    public ResponseEntity<?> getPreData(){
        List<PrepopulatedArticle> preData = prepopulatedArticleService.getPreData();
        if (preData.size() == 0)
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(preData);
    }
    @GetMapping("/get/self-article/{patientId}")
    @PreAuthorize("hasAnyRole('ROLE_DOCTOR','ROLE_USER')")
    public ResponseEntity<?> getPersonalized(@PathVariable UUID patientId){
        List<PersonalArticle> byUser = personalizedArticleService.getByUser(patientId);
        if (byUser != null)
            return ResponseEntity.ok(byUser);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("forgot-password/{email}")
    public ResponseEntity<?> forgotPassword(@PathVariable String email) {
        return emailService.forgotPassword(email);
    }
    @PostMapping("add-device-token")
    public ResponseEntity<?> addDeviceToken(@RequestBody DeviceTokenBody deviceTokenBody){
        boolean added = deviceTokenService.addDeviceToken(deviceTokenBody.getPatientId(),deviceTokenBody.getToken());
        if (added)
            return ResponseEntity.ok("Token added");
        return ResponseEntity.noContent().build();
    }
    @PostMapping("get-device-token")
    public ResponseEntity<?> getDeviceToken(@RequestBody UserIdBody user){
        String deviceToken = deviceTokenService.getDeviceToken(user.getPatientId());
        if (deviceToken == null)
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(deviceToken);
    }
}