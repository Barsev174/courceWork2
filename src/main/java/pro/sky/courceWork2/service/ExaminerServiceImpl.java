package pro.sky.courceWork2.service;

import org.springframework.stereotype.Service;
import pro.sky.courceWork2.model.Question;
import pro.sky.courceWork2.exception.NotEnoughQuestionsException;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
@Service
public class ExaminerServiceImpl implements ExaminerService{
    private final QuestionService questionService;

    public ExaminerServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
    }
    @Override
    public Collection<Question> getQuestion(int amount) {
        Collection<Question> questions = questionService.getAll();
        if (amount > questions.size()) {
            throw new NotEnoughQuestionsException("Запрашиваемое количество вопросов меньше фактического");
        }
        Set<Question> resultQuestions = new HashSet<>();
        while (resultQuestions.size() < amount) {
            resultQuestions.add(questionService.getRandomQuestion());
        } return resultQuestions;
    }

}
