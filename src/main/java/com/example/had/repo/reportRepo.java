package com.example.had.repo;

import com.example.had.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface reportRepo extends JpaRepository<Report,String> {
}
