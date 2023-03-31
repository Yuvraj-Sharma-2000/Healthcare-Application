package com.example.had.repository;

import com.example.had.entity.PersonalArticle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository("personal_article")
public interface PersonalArticleRepository extends JpaRepository<PersonalArticle, UUID>
{
}
