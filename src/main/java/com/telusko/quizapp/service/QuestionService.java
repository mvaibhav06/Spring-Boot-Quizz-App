package com.telusko.quizapp.service;

import com.telusko.quizapp.entity.Question;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface QuestionService {
    ResponseEntity<List<Question>> getAll();
    ResponseEntity<List<Question>> getQuestionsByCategory(String category);

    ResponseEntity<Question> addQuestion(Question question);

    String deleteQuestionById(int id);
}
