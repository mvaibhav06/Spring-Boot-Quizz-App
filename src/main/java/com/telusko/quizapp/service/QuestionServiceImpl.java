package com.telusko.quizapp.service;

import com.telusko.quizapp.dao.QuestionDAOImpl;
import com.telusko.quizapp.entity.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService{

    private QuestionDAOImpl dao;

    @Autowired
    public QuestionServiceImpl(QuestionDAOImpl questionDAO){
        dao = questionDAO;
    }
    @Override
    public ResponseEntity<List<Question>> getAll() {
        try {
            return new ResponseEntity<>(dao.getAll(), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {

        try {
            return new ResponseEntity<>(dao.getQuestionsByCategory(category), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);

    }

    @Override
    @Transactional
    public ResponseEntity<Question> addQuestion(Question question) {
        return new ResponseEntity<>(dao.addQuestion(question), HttpStatus.CREATED);
    }

    @Override
    @Transactional
    public String deleteQuestionById(int id) {
        return dao.deleteQuestionById(id);
    }
}
