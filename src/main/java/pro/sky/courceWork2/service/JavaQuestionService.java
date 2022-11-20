package pro.sky.courceWork2.service;

import org.springframework.stereotype.Service;
import pro.sky.courceWork2.exception.QuestionAlreadyExistException;
import pro.sky.courceWork2.exception.QuestionNotFoundException;
import pro.sky.courceWork2.model.Question;

import java.util.*;

@Service
public class JavaQuestionService implements QuestionService {
    private final Set<Question> questions;
    private final Random random;

    public JavaQuestionService() {
        this.questions = new HashSet<>();
        this.random = new Random();
    }

    @Override
    public Question add(String question, String answer) {

        return add(new Question(question,answer));
    }

    @Override
    public Question add(Question question) {
        if (!questions.add(question)) {
            throw new QuestionAlreadyExistException();
        }
        return question;
    }

    @Override
    public Question remove(Question question) {
        if (!questions.remove(question)) {
            throw new QuestionNotFoundException();
        }
        return question;
    }

    @Override
    public Collection<Question> getAll() {
        return new HashSet<>(questions);
    }

    @Override
    public Question getRandomQuestion() {
        if (questions.size() == 0) {
            return null;
        }
        return new ArrayList<>(questions).get(random.nextInt(questions.size()));
    }
}
