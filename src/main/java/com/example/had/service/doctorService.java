package com.example.had.service;

import com.example.had.entity.Chat;
import com.example.had.entity.Doctor;
import com.example.had.entity.User;
import com.example.had.repo.doctorRepo;
import com.example.had.repo.userRepo;
import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.util.List;

public class doctorService {
    private doctorRepo doctorRepo;
    private userRepo userRepo;

    public doctorService() {
    }
    public void addDoctor(){
        Faker faker = new Faker();
        for (int i = 0; i < 5; i++) {

            Doctor doctor = new Doctor(
                    faker.internet().emailAddress(),
                    faker.name().firstName(),
                    faker.name().lastName(),
                    faker.name().username(),
                    "Male",
                    faker.number().numberBetween(26, 70),
                    faker.educator().course(),
                    faker.educator().course(),
                    faker.number().numberBetween(0, 40),
                    faker.address().fullAddress(),
                    faker.number().digits(13).toString(),
                    faker.internet().url(),
                    faker.number().numberBetween(0, 5),
                    faker.number().numberBetween(1, 20),
                    faker.number().numberBetween(1, 20),
                    faker.number().digits(10).toString(),
                    faker.date().birthday().toString()
            );
            Chat chat = new Chat(
                    faker.medical().symptoms().toString(),
                    faker.date().birthday().toString()
            );
            User user = new User(
                    faker.internet().emailAddress(),
                    faker.name().firstName(),
                    faker.name().lastName(),
                    faker.funnyName().name(),
                    faker.dog().gender(),
                    LocalDate.now(),
                    faker.number().digits(10).toString(),
                    faker.address().streetAddress(),
                    faker.date().birthday().toString(),
                    faker.number().numberBetween(1, 100)
            );
            userRepo.save(user);
            chat.setUser(user);
            doctor.addChat(chat);
            doctorRepo.save(doctor);
        }
    }
    public List<Doctor> getDoctors(){
        return doctorRepo.findAll();
    }
}
