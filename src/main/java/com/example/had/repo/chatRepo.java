package com.example.had.repo;

import com.example.had.entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface chatRepo extends JpaRepository<Chat,String> {
}
