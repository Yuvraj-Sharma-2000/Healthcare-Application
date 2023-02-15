package com.example.had.service;

import com.example.had.entity.Articles;
import com.example.had.repo.articleRepo;
import com.github.javafaker.Faker;
import org.springframework.stereotype.Service;

@Service
public class articleService {
    private final articleRepo articleRepo;

    public articleService(articleRepo articleRepo) {
        this.articleRepo = articleRepo;
    }
    public void addArticles(){
        Faker faker = new Faker();
        for (int i = 0; i < 10; i++) {
            articleRepo.save(
              new Articles(
                      faker.book().genre(),
                      faker.number().toString(),
                      faker.number().toString(),
                      faker.rickAndMorty().character(),
                      faker.random().hex()
                      )
            );
        }
    }
}
