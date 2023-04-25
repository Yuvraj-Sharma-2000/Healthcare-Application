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
import com.example.had.response.Severity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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

//--------------TIMESTAMP BREAK -----------------
//                // Convert the string to a LocalDateTime object
//                String dateStr = auth.getLastLogin();
//                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E MMM dd HH:mm:ss z yyyy");
//                LocalDateTime date = LocalDateTime.parse(dateStr, formatter);
//
//                // Get the current system date as a LocalDateTime object
//                LocalDateTime currentDate = LocalDateTime.now();
//
//                // Calculate the difference between the two dates in days
//                long difference = ChronoUnit.DAYS.between(date, currentDate);
//
//                user.setActive(difference <= 5);
            }

            System.out.println(doctor.getEmail()+" registered patients send");

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

            System.out.println(doctorRepository.getOne(doctorId).getEmail()+" requested patients send");

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

            System.out.println(doctor.getEmail() + " accepted "+user.getEmail());

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

                System.out.println(" details of USER "+user.getEmail()+" send to DOCTOR "+ doctorRepository.getOne(doctorId).getEmail());

                return user;
            }

            System.out.println(doctorRepository.getOne(doctorId).getEmail()+" not connected to "+ user.getEmail());

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Doctor getProfile(UUID doctorId) {
        try{
            Doctor doctor = doctorRepository.findById(doctorId).get();

            System.out.println("Send profile of DOCTOR "+doctor.getEmail());

            return doctor;
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

            System.out.println("updated profile of DOCTOR "+doctorRepository.getOne(doctorId).getEmail());

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
                doctor.setPatientCount(0);
                doctor.setChatList(null);
            }

            System.out.println("Send DOCTORS list");

            return doctorList;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void reject(UUID doctorId, UUID userId) {
        try {
            User user = userRepository.findById(userId).get();
            Doctor doctor = doctorRepository.findById(doctorId).get();

            requestRepository.deleteByUserAndDoctor(user,doctor);

            System.out.println("DOCTOR "+doctor.getEmail()+" rejected request of USER "+user.getEmail());

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public boolean updatePassword(String username, String password) {
        try {
            authRepository.updatePasswordByUsername(passwordEncoder.encode(password),username);

            System.out.println("DOCTOR "+username+" update password");

            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    public List<Severity> getSeverityList(UUID doctorId) {
        try {
            List<User> userList = userRepository.findByDoctor_Id(doctorId);
            List<Severity> severityList = new ArrayList<>();

            int critical = 0 ;
            int high = 0;
            int moderate = 0;
            int low = 0;
            for (User user : userList) {
                if (user.getDepressionSeverity() > 75) {
                    critical++;
                } else if (user.getDepressionSeverity() > 50) {
                    high++;
                } else if (user.getDepressionSeverity() > 25) {
                    moderate++;
                } else {
                    low++;
                }
            }

            severityList.add(new Severity(doctorId, "low", low));
            severityList.add(new Severity(doctorId,"moderate",moderate));
            severityList.add(new Severity(doctorId,"high",high));
            severityList.add(new Severity(doctorId,"critical",critical));

            return severityList;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}