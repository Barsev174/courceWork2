package pro.sky.courceWork2.service;


import org.springframework.stereotype.Service;
import pro.sky.courceWork2.model.Question;

import java.util.Collection;
@Service
public interface ExaminerService {
    Collection<Question> getQuestion(int amount);
}
