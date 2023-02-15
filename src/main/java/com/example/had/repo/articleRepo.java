package com.example.had.repo;

import com.example.had.entity.Articles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface articleRepo extends JpaRepository<Articles,String> {
}
