package com.example.had.contoller;

import com.example.had.response.PlotWeekScore;
import com.example.had.service.DoctorService;
import com.example.had.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Controller
@RequestMapping("/analytics")
public class AnalyticsController {
    private final UserService userService;
    private final DoctorService doctorService;

    public AnalyticsController(UserService userService, DoctorService doctorService) {
        this.userService = userService;
        this.doctorService = doctorService;
    }
    @GetMapping("/plot-line-chart/{patientId}/week/{weekNumber}")
    public ResponseEntity<?> getWeekScore(@PathVariable UUID patientId, @PathVariable int weekNumber){
        PlotWeekScore score = userService.getWeekScore(patientId, weekNumber);
        if(score!=null)
            return ResponseEntity.ok(score);
        return ResponseEntity.badRequest().body("Not able to find");
    }
}