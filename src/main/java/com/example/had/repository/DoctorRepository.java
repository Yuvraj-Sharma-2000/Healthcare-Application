package com.example.had.repository;

import com.example.had.entity.Doctor;
import com.example.had.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

public interface DoctorRepository extends JpaRepository<Doctor, UUID> {
    @Transactional
    @Modifying
    @Query("update Doctor d set d.isVerified = ?1 where d.id = ?2")
    int updateIsVerifiedById(boolean isVerified, UUID id);
    @Query("select d from Doctor d where d.isVerified = ?1 order by d.registrationStamp DESC")
    List<Doctor> findByIsVerifiedOrderByRegistrationStampDesc(boolean isVerified);
    @Transactional
    @Modifying
    @Query("update Doctor d set d.address = ?1, d.contact = ?2, d.degree = ?3, d.patientLimit = ?4, d.specialisation = ?5 " +
            "where d.id = ?6")
    int updateAddressAndContactAndDegreeAndPatientLimitAndSpecialisationById(String address, String contact, String degree, int patientLimit, String specialisation, UUID id);
    @Query("select d from Doctor d where d.email = ?1 and d.isVerified = ?2")
    Doctor findByEmailAndIsVerified(String email, boolean isVerified);
    @Transactional
    @Modifying
    @Query("update Doctor d set d.forgotPassword = ?1 where d.email = ?2")
    int updateForgotPasswordByEmail(boolean forgotPassword, String email);

    @Query("select d from Doctor d where d.patientLimit > d.patientCount and d.isVerified = true ")
    List<Doctor> findByPatientLimitAndPatientCount();

    @Query("select d from Doctor d where upper(d.email) = upper(?1)")
    Doctor findByEmailIgnoreCase(String email);

//    List<Doctor> findByIsVerifiedOrderByRegistrationStampDesc(boolean b);
}
