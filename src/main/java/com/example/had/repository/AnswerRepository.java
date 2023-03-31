package com.example.had.repository;

import com.example.had.entity.Answers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository("answer")
public interface AnswerRepository extends JpaRepository<Answers, UUID> {
}
