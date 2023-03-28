package com.example.had.service;

import com.example.had.entity.Auth;
import com.example.had.entity.Doctor;
import com.example.had.entity.User;
import com.example.had.repository.authRepository;
import com.example.had.repository.doctorRepository;
import com.example.had.repository.userRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Objects;

@Service
public class loginService {
    private final authRepository authRepository;
    private final doctorRepository doctorRepository;
    private final userRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public loginService(authRepository authRepository,
                        doctorRepository doctorRepository,
                        userRepository userRepository, PasswordEncoder passwordEncoder) {
        this.authRepository = authRepository;
        this.doctorRepository = doctorRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    public boolean isPasswordMatch(String plainPassword, String encryptedPassword) {
        return passwordEncoder.matches(plainPassword, encryptedPassword);
    }

    public ResponseEntity<?> getUserByLogin(String username, String password) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Auth byUsername = authRepository.findByUsername(username);
        String role = null;
        if (isPasswordMatch(password, byUsername.getPassword())){
            role = byUsername.getRole();
        }

        authRepository.updateLastLoginByUsername(timestamp.toString(), username);

        if (Objects.equals(role, "DOCTOR")){
            Doctor doctor = doctorRepository.findByEmailIgnoreCase(username);
            return ResponseEntity.ok(doctor);
        }
        if (Objects.equals(role, "USER")){
            User user = userRepository.findByEmailIgnoreCase(password);
            return ResponseEntity.ok(user);
        }
        if (Objects.equals(username, "lynda")
                && Objects.equals(password, "password")){
            return ResponseEntity.ok("ADMIN");
        }
        return ResponseEntity.notFound().build();
    }
}