package com.example.had.contoller;

import com.example.had.request.QuestionAddBody;
import com.example.had.response.WeekQuestions;
import com.example.had.service.AdminService;
import com.example.had.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService;
    private final UserService userService;

    public AdminController(AdminService adminService, UserService userService) {
        this.adminService = adminService;
        this.userService = userService;
    }
    @PostMapping("/add/questions")
    public ResponseEntity<?> addQuestion(@RequestBody List<QuestionAddBody> question){
        boolean added = adminService.addQuestion(question);
        if (added)
            return ResponseEntity.ok("Added Successfully");
        return ResponseEntity.unprocessableEntity().body("Not Able to add");
    }

    @GetMapping("/get/full-week/{weekNumber}")
    public ResponseEntity<?> getFullWeek(@PathVariable int weekNumber){
        List<WeekQuestions> fullWeek = userService.getFullWeek(weekNumber);
        if (fullWeek!=null)
            return ResponseEntity.ok(fullWeek);
        return ResponseEntity.unprocessableEntity().body("Can not retrieve");

    }
}
