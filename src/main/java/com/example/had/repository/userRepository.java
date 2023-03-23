package com.example.had.repository;

import com.example.had.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface userRepository extends JpaRepository<User, UUID> {
    @Transactional
    @Modifying
    @Query("update User u set u.isActive = ?1 where u.email = ?2")
    int updateIsActiveByEmail(boolean isActive, String email);
    @Transactional
    @Modifying
    @Query("update User u set u.address = ?1, u.contact = ?2 where u.id = ?3")
    int updateAddressAndContactById(String address, String contact, UUID id);
    @Query("select u from User u where upper(u.email) = upper(?1)")
    User findByEmailIgnoreCase(String email);
    @Query("select u from User u where u.doctor.id = ?1")
    List<User> findByDoctor_Id(UUID id);

    Optional<User> findById(UUID uuid);
}
