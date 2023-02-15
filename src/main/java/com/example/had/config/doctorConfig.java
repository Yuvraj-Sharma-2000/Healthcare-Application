package com.example.had.config;

import com.example.had.repo.doctorRepo;
import com.example.had.repo.userRepo;
import com.example.had.service.doctorService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class doctorConfig {
//    @Bean
    CommandLineRunner commandLineRunner(doctorRepo doctorRepo, userRepo userRepo){
        return args -> {
            doctorService doctorService = new doctorService();
            doctorService.addDoctor();
        };
    }
}
