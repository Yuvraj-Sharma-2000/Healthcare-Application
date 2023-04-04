package com.example.had.contoller;


import com.example.had.entity.Doctor;
import com.example.had.entity.PrepopulatedArticle;
import com.example.had.request.LoginRequestBody;
import com.example.had.request.PersonalizedArticle;
import com.example.had.service.DoctorService;
import com.example.had.service.LoginService;
import com.example.had.service.PrepopulatedArticleService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.constraints.NotNull;
import java.util.List;

@Controller
@RequestMapping("/")
@PreAuthorize("permitAll()")
public class TemplateController {
    private final LoginService loginService;
    private final DoctorService doctorService;
    private final PrepopulatedArticleService prepopulatedArticleService;


    public TemplateController(LoginService loginService,
                              DoctorService doctorService,
                              PrepopulatedArticleService prepopulatedArticleService) {
        this.loginService = loginService;
        this.doctorService = doctorService;
        this.prepopulatedArticleService = prepopulatedArticleService;
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
}