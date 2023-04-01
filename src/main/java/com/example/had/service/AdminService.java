package com.example.had.service;

import com.example.had.entity.Question;
import com.example.had.repository.QuestionRepository;
import com.example.had.request.QuestionAddBody;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    private final QuestionRepository questionRepository;

    public AdminService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public boolean addQuestion(QuestionAddBody question) {
        try {
            questionRepository.save(
                    new Question(
                            question.getQuestion(),
                            question.getQuestionType(),
                            question.getOption1(),
                            question.getOption2(),
                            question.getOption3(),
                            question.getOption4(),
                            question.getValue1(),
                            question.getValue2(),
                            question.getValue3(),
                            question.getValue4(),
                            question.getWeekNumber(),
                            question.getSessionNumber()
                    )
            );

            System.out.println("Admin Saved Questions");

            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }
}
