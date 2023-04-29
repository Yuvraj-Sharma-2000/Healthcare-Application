package com.example.had.repository;

import com.example.had.entity.DeviceToken;
import com.example.had.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
@Repository
public interface DeviceTokenRepository extends JpaRepository<DeviceToken, UUID> {
    @Transactional
    @Modifying
    @Query("update DeviceToken d set d.token = ?1 where d.user = ?2")
    int updateTokenByUser(String token, User user);
    @Query("select d from DeviceToken d where d.user.id = ?1")
    DeviceToken findByUser_Id(UUID id);
}
