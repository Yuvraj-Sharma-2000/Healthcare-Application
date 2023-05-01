package com.example.had.service;

import com.example.had.entity.Doctor;
import com.example.had.entity.User;
import com.example.had.repository.AuthRepository;
import com.example.had.repository.DoctorRepository;
import com.example.had.repository.UserRepository;
import com.github.javafaker.Faker;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Objects;

@Service
public class EmailService {
    private final JavaMailSender emailSender;
    private final UserRepository userRepository;
    private final AuthRepository authRepository;
    private final DoctorRepository doctorRepository;

    public EmailService(JavaMailSender emailSender,
                        UserRepository userRepository,
                        AuthRepository authRepository,
                        DoctorRepository doctorRepository) {
        this.emailSender = emailSender;
        this.userRepository = userRepository;
        this.authRepository = authRepository;
        this.doctorRepository = doctorRepository;
    }

    public ResponseEntity<?> forgotPassword(String email) {
        Faker faker = new Faker();
        String newPassword = faker.internet().password();
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        try{
            User user = userRepository.findByEmail(email);
            if(!Objects.isNull(user)) {
                userRepository.updateForgotPasswordByEmail(true,email);
                createMail(user.getEmail(), "Your new credentials", newPassword);
                authRepository.updatePasswordByUsername(passwordEncoder.encode(newPassword),user.getEmail());
                return ResponseEntity.ok("check your email for credentials");
            }
            System.out.println("USER not found");
            Doctor doctor = doctorRepository.findByEmailAndIsVerified(email, true);
            if (!Objects.isNull(doctor)){
                doctorRepository.updateForgotPasswordByEmail(true,email);
                createMail(doctor.getEmail(), "Your new credentials", newPassword);
                authRepository.updatePasswordByUsername(passwordEncoder.encode(newPassword),doctor.getEmail());
                return ResponseEntity.ok("check your email for credentials");
            }
            System.out.println("DOCTOR NOT FOUND");
            return ResponseEntity.badRequest().body("NO ENTITY FOUND");

        }catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return ResponseEntity.badRequest().body("Not able to process request");
    }
    public void createMail(String to, String subject , String password) throws MessagingException{
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper= new MimeMessageHelper(message,true);
        helper.setFrom("Smtp.Email.Sender.User@gmail.com");
        helper.setTo(to);
        helper.setSubject(subject);
        String htmlMsg = "<p><b>Your Login details for PUSH-D application</b><br><b>Email: </b> " + to + " <br><b>Password: </b> " + password;
        message.setContent(htmlMsg,"text/html");
        emailSender.send(message);
    }
    public void verifyMail(String to, String subject) throws MessagingException{
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper= new MimeMessageHelper(message,true);
        helper.setFrom("Smtp.Email.Sender.User@gmail.com");
        helper.setTo(to);
        helper.setSubject(subject);
        String htmlMsg = "<p><b>Hurray! You are verified by Admin</b><br> ";
        message.setContent(htmlMsg,"text/html");
        emailSender.send(message);
    }
}