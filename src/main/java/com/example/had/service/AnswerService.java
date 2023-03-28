package com.example.had.service;

import com.example.had.entity.Answers;
import com.example.had.entity.User;
import com.example.had.repository.answerRepository;
import com.example.had.repository.userRepository;
import com.example.had.request.answersBody;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AnswerService {
    private final answerRepository answerRepository;
    private final userRepository userRepository;

    public AnswerService(answerRepository answerRepository,userRepository userRepository) {
        this.answerRepository = answerRepository;
        this.userRepository = userRepository;
    }

    public boolean addAnswer(answersBody answersBody, UUID patientId, int weekNumber, int sessionNumber) {
        try{
            User user = userRepository.findById(patientId).get();   // get the user
            Answers answers = answerRepository.save(
                    new Answers(
                            answersBody.getOption1(),
                            answersBody.getOption2(),
                            answersBody.getOption3(),
                            answersBody.getOption4(),
                            answersBody.getValue1(),
                            answersBody.getValue2(),
                            answersBody.getValue3(),
                            answersBody.getValue4(),
                            weekNumber,
                            sessionNumber,
                            user
                            )
            );
            List<Answers> answersList = user.getAnswers();          // get this user's answers
            answersList.add(answers);                               // add this particular answer to answers list
            user.setAnswers(answersList);                           // add the answer list to user

            answerRepository.save(answers);
            userRepository.save(user);

//            System.out.println(user.getFirstName() + " added answers");
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }
}
