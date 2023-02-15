package com.example.had.repo;

import com.example.had.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface doctorRepo extends JpaRepository<Doctor,String> {
}
