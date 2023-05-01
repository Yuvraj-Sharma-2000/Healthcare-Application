package com.example.had.contoller;

import com.example.had.request.PodcastBody;
import com.example.had.request.QuestionAddBody;
import com.example.had.response.WeekQuestions;
import com.example.had.service.AdminService;
import com.example.had.service.PodcastService;
import com.example.had.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminController {
    private final AdminService adminService;
    private final UserService userService;
    private final PodcastService podcastService;

    public AdminController(AdminService adminService, UserService userService, PodcastService podcastService) {
        this.adminService = adminService;
        this.userService = userService;
        this.podcastService = podcastService;
    }
    @PostMapping("/add/questions")
    public ResponseEntity<?> addQuestion(@RequestBody List<QuestionAddBody> question){
        boolean added = adminService.addQuestion(question);
        if (added)
            return ResponseEntity.ok("Added Successfully");
        return ResponseEntity.unprocessableEntity().body("Not Able to add");
    }
    @PostMapping("/add-podcast")
    public ResponseEntity<?> addPodcast(@NotNull @RequestBody List<PodcastBody> podcastBody){
        boolean added = podcastService.addPodcast(podcastBody);
        if (added)
            return ResponseEntity.ok(podcastBody.size()+" Podcasts added");
        return ResponseEntity.unprocessableEntity().body("Not able add");
    }
    @GetMapping("/get/full-week/{weekNumber}")
    public ResponseEntity<?> getFullWeek(@PathVariable int weekNumber){
        List<WeekQuestions> fullWeek = userService.getFullWeek(weekNumber);
        if (fullWeek!=null)
            return ResponseEntity.ok(fullWeek);
        return ResponseEntity.unprocessableEntity().body("Can not retrieve");

    }
}
