package com.example.had.repository;

import com.example.had.entity.Doctor;
import com.example.had.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    @Query("select u from User u where u.doctor = ?1")
    List<User> findByDoctor(Doctor doctor);
    @Transactional
    @Modifying
    @Query("update User u set u.depressionSeverity = ?1 where u.id = ?2")
    int updateDepressionSeverityById(float depressionSeverity, UUID id);
    @Transactional
    @Modifying
    @Query("update User u set u.forgotPassword = ?1 where u.email = ?2")
    int updateForgotPasswordByEmail(boolean forgotPassword, String email);
    @Query("select u from User u where u.doctor.email = ?1")
    List<User> findByDoctor_Email(String email);
    @Query("select u from User u inner join u.personalArticles personalArticles where personalArticles.doctor.email = ?1")
    List<User> findByPersonalArticles_Doctor_Email(String email);
    @Query("select u from User u where u.id = ?1 and u.report.weekNumber = ?2")
    List<User> findByIdAndReport_WeekNumber(UUID id, int weekNumber);
    @Query("select u from User u inner join u.answers answers where u.email = ?1 and answers.weekNumber = ?2")
    List<User> findByEmailAndAnswers_WeekNumber(String email, int weekNumber);
    @Query("select u from User u inner join u.answers answers where answers.weekNumber = ?1")
    User findByAnswers_WeekNumber(int weekNumber);
    @Query("select u from User u where u.email = ?1")
    User findByEmail(String email);
    @Transactional
    @Modifying
    @Query("update User u set u.isActive = ?1 where u.email = ?2")
    int updateIsActiveByEmail(boolean isActive, String email);
    @Transactional
    @Modifying
    @Query("update User u set u.address = ?1, u.contact = ?2 where u.id = ?3")
    int updateAddressAndContactById(String address, String contact, UUID id);

    @Query("select u from User u where u.doctor.id = ?1")
    List<User> findByDoctor_Id(UUID id);

    List<User> findByDoctor_IdAndDoctor_Email(UUID id, String email);

    Optional<User> findById(UUID uuid);
    @Query("select u from User u where u.doctor.id is null")
    List<User> findByDoctorLike();
}
