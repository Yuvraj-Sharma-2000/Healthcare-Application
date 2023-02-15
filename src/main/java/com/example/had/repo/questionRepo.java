package com.example.had.repo;

import com.example.had.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface questionRepo extends JpaRepository<Question,String> {
}
