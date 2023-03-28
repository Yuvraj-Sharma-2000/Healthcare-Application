package com.example.had.sample;

import com.example.had.entity.User;
import com.example.had.repository.*;

import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class Dummydata {
    Faker faker = new Faker();

    @Autowired
    private answerRepository answerRepository;
    @Autowired
    private authRepository authRepository;

    @Autowired
    private doctorConnectionRequestRepository doctorConnectionRequestRepository;

    @Autowired
    private doctorRepository doctorRepository;

    @Autowired
    private questionRepository questionRepository;

    @Autowired
    private  userRepository userRepository;

//    public void addData()
//    {
//        for(int i=0 ; i<20; i++)
//        {
//            new User(
//                    faker.internet().emailAddress(),
//                    faker.name().firstName(),
//                    faker.name().lastName(),
//                    faker.name().nameWithMiddle(),
//                    faker.demographic().sex(),
//
//            )
//
//        }
//    }
}
