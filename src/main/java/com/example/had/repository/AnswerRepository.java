package com.example.had.repository;

import com.example.had.entity.Answers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository("answer")
public interface AnswerRepository extends JpaRepository<Answers, UUID> {
    @Transactional
    @Modifying
    @Query("update Answers a set a.answer_value = ?1, a.answer_options = ?2 where a.id = ?3")
    int updateAnswer_valueAndAnswer_optionsById(List<Float> answer_value, List<String> answer_options, UUID id);
    @Query("select a from Answers a where a.user.id = ?1 and a.weekNumber = ?2 and a.sessionNumber = ?3")
    Answers findByUser_IdAndWeekNumberAndSessionNumber(UUID id, int weekNumber, int sessionNumber);
    @Query("select a from Answers a where a.user.id = ?1 and a.weekNumber = ?2 order by a.sessionNumber DESC")
    List<Answers> findByUser_IdAndWeekNumberOrderBySessionNumberDesc(UUID id, int weekNumber);
    @Query("select a from Answers a where a.user.id = ?1 and a.weekNumber = ?2")
    List<Answers> findByUser_IdAndWeekNumber(UUID id, int weekNumber);
}
