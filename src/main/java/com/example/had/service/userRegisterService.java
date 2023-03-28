package com.example.had.service;

import com.example.had.entity.Auth;
import com.example.had.entity.User;
import com.example.had.repository.authRepository;
import com.example.had.repository.userRepository;
import com.example.had.request.userRegisterRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalTime;
import java.util.Date;

@Service
public class userRegisterService {
    private final authRepository authRepository;
    private final userRepository userRepository;

    public userRegisterService(authRepository authRepository, userRepository userRepository) {
        this.authRepository = authRepository;
        this.userRepository = userRepository;
    }

    public ResponseEntity<?> registerUser(userRegisterRequest userRegisterRequest) {
        try {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            authRepository.save(new Auth(userRegisterRequest.getEmail(),
                    userRegisterRequest.getPassword(),
                    "USER",
                    timestamp.toString()
                    ));

            userRepository.save(new User(
                    userRegisterRequest.getEmail(),
                    userRegisterRequest.getFirstName(),
                    userRegisterRequest.getLastName(),
                    userRegisterRequest.getMiddleName(),
                    userRegisterRequest.getGender(),
                    userRegisterRequest.getDob(),
                    userRegisterRequest.getContact(),
                    userRegisterRequest.getAddress(),
                    new Timestamp(new Date().getTime()).toString(),
                    0
            ));
            return ResponseEntity.ok("Registered Successfully");
        }catch (Exception e){
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body("Not able to register");
        }
    }
}
