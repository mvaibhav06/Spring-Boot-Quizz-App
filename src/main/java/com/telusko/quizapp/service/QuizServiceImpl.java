package com.telusko.quizapp.service;

import com.telusko.quizapp.dao.QuestionDAOImpl;
import com.telusko.quizapp.dao.QuizDAO;
import com.telusko.quizapp.entity.Question;
import com.telusko.quizapp.entity.Quiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizServiceImpl implements QuizService{

    private QuizDAO quizDAO;
    private QuestionDAOImpl questionDAO;

    @Autowired
    public QuizServiceImpl(QuizDAO quizDAO, QuestionDAOImpl questionDAO){
        this.quizDAO = quizDAO;
        this.questionDAO = questionDAO;
    }


    @Override
    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {

        List<Question> questions = questionDAO.findRandomQuestionsByCategory(category, numQ);

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizDAO.save(quiz);

    }
}
