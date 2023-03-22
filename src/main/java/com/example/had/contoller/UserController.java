package com.example.had.contoller;

import com.example.had.entity.Question;
import com.example.had.entity.User;
import com.example.had.service.AnswerService;
import com.example.had.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.example.had.request.answersBody;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/user")
@PreAuthorize("hasRole('ROLE_USER')")
public class UserController {
    private final UserService userService;
    private final AnswerService answerService;

    public UserController(UserService userService, AnswerService answerService) {
        this.userService = userService;
        this.answerService = answerService;
    }
    @GetMapping("/get/session/{weekNumber}/{sessionNumber}")
    public List<Question> getInitialQuestions(
            @PathVariable int weekNumber,
            @PathVariable int sessionNumber
    ){
        return userService.getQuestions(weekNumber,sessionNumber);
    }
    @PostMapping("/post/question-answers/{patientId}/{weekNumber}/{sessionNumber}")
    public ResponseEntity<?> saveAnswers(
            @RequestBody answersBody answersBody,
            @PathVariable UUID patientId,
            @PathVariable int weekNumber,
            @PathVariable int sessionNumber
    ){
        boolean added = answerService.addAnswer(answersBody, patientId, weekNumber, sessionNumber);
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
}