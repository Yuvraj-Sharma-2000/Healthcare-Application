package com.example.had.service;

import com.example.had.entity.Auth;
import com.example.had.entity.Doctor;
import com.example.had.entity.DoctorConnectionRequest;
import com.example.had.entity.User;
import com.example.had.repository.AuthRepository;
import com.example.had.repository.DoctorConnectionRequestRepository;
import com.example.had.repository.DoctorRepository;
import com.example.had.repository.UserRepository;
import com.example.had.request.DoctorProfileBody;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class DoctorService {
    private final UserRepository userRepository;
    private final AuthRepository authRepository;
    private final DoctorRepository doctorRepository;
    private final DoctorConnectionRequestRepository requestRepository;
    private final PasswordEncoder passwordEncoder;

    public DoctorService(UserRepository userRepository,
                         AuthRepository authRepository,
                         DoctorRepository doctorRepository,
                         DoctorConnectionRequestRepository requestRepository,
                         PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.authRepository = authRepository;
        this.doctorRepository = doctorRepository;
        this.requestRepository = requestRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> getRegisteredPatients(UUID doctorId) {
        try {
            Doctor doctor = doctorRepository.findById(doctorId).get();
            List<User> userList = doctor.getUserList();
            for (User user: userList){
                user.setReport(null);
                user.setDoctor(null);
                user.setChatList(null);
                Auth auth = authRepository.findFirstByUsername(user.getEmail());
                if (Timestamp.valueOf(auth.getLastLogin()).compareTo(new Timestamp(System.currentTimeMillis())) > 5){
                    user.setActive(false);
                }
                else {
                    user.setActive(true);
                }
            }
            return userList;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<User> getRequests(UUID doctorId){
        try {
            List<DoctorConnectionRequest> requests = requestRepository.findByDoctor_Id(doctorId);
            List<User> userList = new ArrayList<>();
            for (DoctorConnectionRequest request: requests) {
                User user = userRepository.findById(request.getUser().getId()).get();
                userList.add(user);
            }
            return userList;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public ResponseEntity<String> getResponse(UUID doctorId, UUID userId) {
        try {
            DoctorConnectionRequest request = requestRepository.findByUser_IdAndDoctor_Id(userId,doctorId);
            if (request==null){
                return ResponseEntity.noContent().build();
            }
//        --------- Accepted request ----------
            User user = request.getUser();                      // get requested doctor
            Doctor doctor = request.getDoctor();                // get requested user

            List<DoctorConnectionRequest> requestsByUser = requestRepository.findByUser_Id(user.getId());
            requestRepository.deleteAll(requestsByUser);        // purge all requests related to this user

            List<User> doctorUserList = doctor.getUserList();   // get all users connected to that doctor
            doctorUserList.add(user);                           // add this user to doctor
            user.setDoctor(doctor);                             // add this doctor to user
            doctorRepository.save(doctor);                      // persist in database
            userRepository.save(user);

            return ResponseEntity.ok("Request confirmed");
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public User getPatient(UUID doctorId, UUID patientId) {
        try
        {
//            Check if doctor is connected to user then send the user details else null
            User user = userRepository.findById(patientId).get();
            if (doctorRepository.findById(doctorId).get().getUserList().contains(user)) {
                user.setDoctor(null);
                user.setChatList(null);
                user.setId(null);
                user.setRegistrationStamp(null);
                user.setAddress(null);
                return user;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Doctor getProfile(UUID doctorId) {
        try{
            return doctorRepository.findById(doctorId).get();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public boolean updateProfile(UUID doctorId, DoctorProfileBody doctorProfileBody) {
        try{
            doctorRepository.updateSpecialisationAndContactAndAddressAndImageUrlAndPatientLimitById(
                    doctorProfileBody.getSpecialization(),
                    doctorProfileBody.getContact(),
                    doctorProfileBody.getAddress(),
                    doctorProfileBody.getImageUrl(),
                    doctorProfileBody.getPatientLimit(),
                    doctorId
            );
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    public List<Doctor> getAllDoctors() {
        try {
            List<Doctor> doctorList = doctorRepository.findByPatientLimitAndPatientCount();
            for (Doctor doctor : doctorList) {
                doctor.setUserList(null);
                doctor.setPatientLimit(0);
                doctor.setId(null);
                doctor.setPatientCount(0);
                doctor.setChatList(null);
            }
            return doctorList;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void reject(UUID doctorId, UUID userId) {
        try {
            requestRepository.deleteByUserAndDoctor(
                    userRepository.findById(userId).get(),
                    doctorRepository.findById(doctorId).get()
            );
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public boolean updatePassword(String username, String password) {
        try {
            authRepository.updatePasswordByUsername(passwordEncoder.encode(password),username);
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }
}