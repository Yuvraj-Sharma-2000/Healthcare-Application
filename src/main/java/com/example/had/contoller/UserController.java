package com.example.had.contoller;

import com.example.had.entity.User;
import com.example.had.request.AnswersBody;
import com.example.had.request.UserProfileUpdateRequest;
import com.example.had.request.updateUserTimestampBody;
import com.example.had.service.AnswerService;
import com.example.had.service.UserService;
import com.example.had.service.LoginService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.UUID;


@Controller
@RequestMapping("/user")
@PreAuthorize("hasRole('ROLE_USER')")
public class UserController {
    private final UserService userService;
    private final AnswerService answerService;
    private final LoginService loginService;


    public UserController(UserService userService,
                          AnswerService answerService,
                          LoginService loginService) {
        this.userService = userService;
        this.answerService = answerService;
        this.loginService = loginService;
    }

    @GetMapping("/get/session/{sessionNumber}/week/{weekNumber}")
    public ResponseEntity<?> getInitialQuestions(
            @PathVariable int weekNumber,
            @PathVariable int sessionNumber
    ){
        return userService.getQuestions(weekNumber,sessionNumber);
    }
    @PostMapping("/post/question-answers")
    public ResponseEntity<?> saveAnswers(@NotNull @RequestBody AnswersBody answersBody){
        boolean added = answerService.addAnswer(answersBody);
        if (added)
            return ResponseEntity.ok("Answers registered successfully");
        return ResponseEntity.badRequest().body("Not able to register answers");
    }
    @GetMapping("/get/profile/{patientId}")
    public ResponseEntity<?> getProfile(@PathVariable UUID patientId){
        User profile = userService.getProfile(patientId);
        if (profile != null)
            return ResponseEntity.ok(profile);
        return ResponseEntity.notFound().build();
    }
    @PostMapping("/update/profile/{patientId}")
    public ResponseEntity<?> updateProfile(@PathVariable UUID patientId,
                                           @RequestBody UserProfileUpdateRequest updateRequest){
        boolean updated = userService.updateProfile(patientId,updateRequest);
        if (updated)
            return ResponseEntity.ok("profile updated successfully");
        return ResponseEntity.notFound().build();
    }
    @PostMapping("/request-doctor/{patienId}/{doctorId}")
    public ResponseEntity<?> requestDcotor(@PathVariable UUID patienId, @PathVariable UUID doctorId){
        boolean requested = userService.requestDoctor(patienId,doctorId);
        if (requested)
            return ResponseEntity.ok("Request Successful");
        return ResponseEntity.unprocessableEntity().body("Not able to register");
    }
    @PostMapping("/update-timestamp")
    public ResponseEntity<?> updateTime(@NotNull @RequestBody updateUserTimestampBody userTimestampBody){
        boolean updated = userService.updateTime(userTimestampBody);
        if (updated)
            return ResponseEntity.ok("Activity Accounted");
        return ResponseEntity.unprocessableEntity().body("You dont want to see this");
    }
}