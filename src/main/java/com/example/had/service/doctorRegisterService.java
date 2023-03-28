package com.example.had.service;

import com.example.had.entity.Auth;
import com.example.had.entity.Doctor;
import com.example.had.repository.doctorRepository;
import com.example.had.request.doctorRegisterRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.had.repository.authRepository;

import java.sql.Timestamp;
import java.util.List;

@Service
public class doctorRegisterService {
    private final authRepository authRepository;
    private final doctorRepository doctorRepository;
    private final PasswordEncoder passwordEncoder;
    public doctorRegisterService(authRepository authRepository, doctorRepository doctorRepository, PasswordEncoder passwordEncoder) {
        this.authRepository = authRepository;
        this.doctorRepository = doctorRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public ResponseEntity registerDoctor(doctorRegisterRequest doctorRegisterRequest) {
        try{
            doctorRepository.save(new Doctor(doctorRegisterRequest.getEmail(),
                    doctorRegisterRequest.getFirstName(),
                    doctorRegisterRequest.getMiddleName(),
                    doctorRegisterRequest.getLastName(),
                    doctorRegisterRequest.getGender(),
                    doctorRegisterRequest.getAge(),
                    doctorRegisterRequest.getDegree(),
                    doctorRegisterRequest.getSpecialisation(),
                    doctorRegisterRequest.getExperience(),
                    doctorRegisterRequest.getAddress(),
                    doctorRegisterRequest.getContact(),
                    doctorRegisterRequest.getImageUrl(),
                    4,
                    doctorRegisterRequest.getPatientLimit(),
                    doctorRegisterRequest.getPatientCount(),
                    doctorRegisterRequest.getRegistrationNumber(),
                    doctorRegisterRequest.getRegistrationStamp()));

//            System.out.println(doctorRegisterRequest.getEmail() + " Requested for DOCTOR autorization");

            return ResponseEntity.ok("Awaiting Response from Admin");
        }catch (Exception e){
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body("Not able to register");
        }
    }

    public ResponseEntity authDoctor(doctorRegisterRequest doctorRegisterRequest) {
        try{
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            doctorRepository.updateIsVerifiedByEmailIgnoreCase(true,doctorRegisterRequest.getEmail());
            authRepository.save(
                    new Auth(doctorRegisterRequest.getEmail()
                    , passwordEncoder.encode(doctorRegisterRequest.getPassword()),
                            "DOCTOR",
                            timestamp.toString()
                    )
            );

//            System.out.println(doctorRegisterRequest.getEmail() + " Registered Successfully");

            return ResponseEntity.ok("Registered Successfully");
        }catch (Exception e){
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body("Not able to register");
        }
    }

    public ResponseEntity getAllRequests() {
        try{
            List<Doctor> byIsVerified = doctorRepository.findByIsVerified(false);
            System.out.println(byIsVerified);

//            System.out.println();

            return ResponseEntity.ok(byIsVerified);
        }catch (Exception e){
            System.out.println("Not able to find");
            return ResponseEntity.noContent().build();
        }
    }
}
