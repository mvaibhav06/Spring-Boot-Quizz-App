package com.telusko.quizapp.dao;

import com.telusko.quizapp.entity.Question;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QuestionDAOImpl  implements QuestionDAO{

    private EntityManager entityManager;

    @Autowired
    public QuestionDAOImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public List<Question> getAll() {

        TypedQuery<Question> query = entityManager.createQuery("from Question ", Question.class);
        List<Question> rs = query.getResultList();
        return rs;
    }

    @Override
    public List<Question> getQuestionsByCategory(String category) {

        TypedQuery<Question> query = entityManager.createQuery("from Question where category=:category", Question.class);
        query.setParameter("category", category);
        return query.getResultList();
    }

    @Override
    public Question addQuestion(Question question) {
        question.setId(0);
        Question dbQuestion =  entityManager.merge(question);
        return dbQuestion;
    }

    @Override
    public String deleteQuestionById(int id) {
        Question question = entityManager.find(Question.class, id);
        entityManager.remove(question);

        return "Question deleted successfully";
    }

    @Override
    public List<Question> findRandomQuestionsByCategory(String category, int numQ) {

        String sql = "select * from questions q where q.category=:category order by RANDOM() LIMIT :numQ";

        Query nativeQuery = (Query) entityManager.createNativeQuery(sql, Question.class);

        nativeQuery.setParameter("category", category);
        nativeQuery.setParameter("numQ", numQ);

        return nativeQuery.getResultList();

    }
}
