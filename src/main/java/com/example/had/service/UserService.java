package com.example.had.service;

import com.example.had.entity.DoctorConnectionRequest;
import com.example.had.entity.Question;
import com.example.had.entity.User;
import com.example.had.repository.*;
import com.example.had.request.UserProfileUpdateRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final QuestionRepository questionRepository;
    private final AuthRepository authRepository;
    private final PasswordEncoder passwordEncoder;
    private final DoctorRepository doctorRepository;
    private final DoctorConnectionRequestRepository doctorConnectionRequestRepository;

    public UserService(UserRepository userRepository,
                       QuestionRepository questionRepository,
                       AuthRepository authRepository,
                       PasswordEncoder passwordEncoder,
                       DoctorRepository doctorRepository,
                       DoctorConnectionRequestRepository doctorConnectionRequestRepository) {
        this.userRepository = userRepository;
        this.questionRepository = questionRepository;
        this.authRepository = authRepository;
        this.passwordEncoder = passwordEncoder;
        this.doctorRepository = doctorRepository;
        this.doctorConnectionRequestRepository = doctorConnectionRequestRepository;
    }

    public ResponseEntity<?> getQuestions(int week, int session) {
        List<Question> questions = questionRepository.findByWeekNumberAndSessionNumber(week, session);
        if (questions.size() == 0)
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(questions);
    }

    public User getProfile(UUID patientId) {
        try {
            return userRepository.findById(patientId).get();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public boolean updateProfile(UUID patientId, UserProfileUpdateRequest updateRequest) {
        try{
            userRepository.updateAddressAndContactById(
                    updateRequest.getAddress(),
                    updateRequest.getContact(),
                    patientId
            );
            authRepository.updatePasswordByUsername(
                    passwordEncoder.encode(updateRequest.getPassword()),
                    userRepository.findById(patientId).get().getEmail()
            );
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean requestDoctor(UUID patienId, UUID doctorId) {
        try{
            doctorConnectionRequestRepository.save(new DoctorConnectionRequest(
               userRepository.findById(patienId).get(),
               doctorRepository.findById(doctorId).get()
            ));
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }
}
