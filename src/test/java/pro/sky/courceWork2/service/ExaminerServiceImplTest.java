package pro.sky.courceWork2.servise;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import pro.sky.courceWork2.exception.NotEnoughQuestionsException;
import pro.sky.courceWork2.model.Question;
import pro.sky.courceWork2.service.ExaminerService;
import pro.sky.courceWork2.service.JavaQuestionService;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

public class ExaminerServiceImplTest {
    @Mock
    private JavaQuestionService javaQuestionService;
    @InjectMocks
    private ExaminerService examinerService;

    private final List<Question> javaQuestion = new ArrayList<>();

    @BeforeEach
    public void beforeEach() {
        javaQuestion.clear();
        javaQuestion.addAll(
                Stream.of(
                        new Question("Вопрос 1", "Ответ 1"),
                        new Question("Вопрос 2", "Ответ 2"),
                        new Question("Вопрос 3", "Ответ 3")
                ).collect(Collectors.toSet())
        );
        when(javaQuestionService.getAll()).thenReturn(javaQuestion);
    }
    @Test
    public void getQuestionNegativeTest() {
        assertThatExceptionOfType(NotEnoughQuestionsException.class)
                .isThrownBy(()->examinerService.getQuestion(-1));
    }

    @Test
    public void getQuestionPositiveTest() {
        when(javaQuestionService.getRandomQuestion()).thenReturn(
                new Question("Вопрос 1", "Ответ 1"),
                new Question("Вопрос 2", "Ответ 2"),
                new Question("Вопрос 1", "Ответ 1"),
                new Question("Вопрос 1", "Ответ 1"),
                new Question("Вопрос 3", "Ответ 3")
        );
        assertThat(examinerService.getQuestion(3))
                .hasSize(3)
                .containsExactlyInAnyOrder(
                        new Question("Вопрос 1", "Ответ 1"),
                        new Question("Вопрос 2", "Ответ 2"),
                        new Question("Вопрос 3", "Ответ 3")
                );
    }
}
