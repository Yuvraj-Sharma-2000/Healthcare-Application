package com.example.had.service;

import com.example.had.entity.Answers;
import com.example.had.entity.User;
import com.example.had.repository.AnswerRepository;
import com.example.had.repository.QuestionRepository;
import com.example.had.repository.UserRepository;
import com.example.had.request.AnswersBody;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class AnswerService {
    private final AnswerRepository answerRepository;
    private final UserRepository userRepository;
    private final QuestionRepository questionRepository;

    public AnswerService(AnswerRepository answerRepository,
                         UserRepository userRepository,
                         QuestionRepository questionRepository) {
        this.answerRepository = answerRepository;
        this.userRepository = userRepository;
        this.questionRepository = questionRepository;
    }
    @Transactional
    public boolean addAnswer(AnswersBody answersBody) {

        System.out.println(answersBody);
        try {
            int weekNumber = answersBody.getWeekNumber();
            int sessionNumber = answersBody.getSessionNumber();
            List<String> answerOptions = answersBody.getAnswer_options();
            List<Float> answerValue = answersBody.getAnswer_value();
            UUID patientId = answersBody.getPatientId();

            Answers answer = answerRepository.findByUser_IdAndWeekNumberAndSessionNumber(patientId, weekNumber, sessionNumber);
            if (Objects.isNull(answer)){
                Answers current_answer = new Answers(
                        weekNumber,
                        sessionNumber,
                        answerValue,
                        answerOptions
                );
                current_answer.setUser(userRepository.findById(patientId).get());
                answerRepository.save(current_answer);
            }
            else {
                answerRepository.updateAnswer_valueAndAnswer_optionsById(answerValue,answerOptions,answer.getId());
            }


            float sum = 0;
            for (Float val : answerValue) {
                sum += val;
            }

            if (sessionNumber==0 && weekNumber==0){
                userRepository.updateWeekDoneAndSessionDoneById(0,0,patientId);
               sum = (sum/10);
            }
            else {
                sum = (sum/5);
                if (sessionNumber==5){
                    userRepository.updateWeekDoneAndSessionDoneById(weekNumber,0,patientId);
                }
                else {
                    userRepository.updateWeekDoneAndSessionDoneById(weekNumber-1,sessionNumber,patientId);
                }
            }

            userRepository.updateDepressionSeverityById(sum,patientId);

            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }
}
