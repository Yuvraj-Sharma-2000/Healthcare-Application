package com.example.had.auth;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
interface ApplicationUserDao {
    Optional<ApplicationUser> selectApplicationUserByUsername(String username);
}
