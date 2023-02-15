package com.example.had.config;

import com.example.had.repo.articleRepo;
import com.example.had.service.articleService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class articleConfig {
//    @Bean
    CommandLineRunner commandLineRunner(articleRepo articleRepo){
        return args -> {
            articleService articleService = new articleService(articleRepo);
            articleService.addArticles();
        };
    }
}
