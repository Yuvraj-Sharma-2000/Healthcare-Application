package com.example.had.repo;

import com.example.had.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface userRepo extends JpaRepository<User,String> {
}
