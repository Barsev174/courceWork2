package pro.sky.courceWork2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.courceWork2.model.Question;
import pro.sky.courceWork2.service.ExaminerService;

import java.util.Collection;

@RestController
public class ExamController {
    private final ExaminerService examinerService;


    public ExamController(ExaminerService examinerService) {
        this.examinerService = examinerService;
    }
    @GetMapping("/get/{amount}")
    public Collection<Question> getRandomQuestion (@PathVariable(value = "amount") int amount){
        return examinerService.getQuestion(amount);
    }

}