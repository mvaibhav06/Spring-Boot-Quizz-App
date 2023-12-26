package com.telusko.quizapp.dao;

import com.telusko.quizapp.entity.Question;

import java.util.List;

public interface QuestionDAO {

    List<Question> getAll();

    List<Question> getQuestionsByCategory(String category);

    Question addQuestion(Question question);

    String deleteQuestionById(int id);

    List<Question> findRandomQuestionsByCategory(String category, int numQ);
}
