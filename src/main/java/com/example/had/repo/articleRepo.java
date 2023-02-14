package com.example.had.repo;

import com.example.had.entity.articles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface articleRepo extends JpaRepository<articles,String> {
}
