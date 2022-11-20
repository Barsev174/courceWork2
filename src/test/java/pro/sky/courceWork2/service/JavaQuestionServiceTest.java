package pro.sky.courceWork2.servise;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.courceWork2.exception.QuestionAlreadyExistException;
import pro.sky.courceWork2.exception.QuestionNotFoundException;
import pro.sky.courceWork2.model.Question;
import pro.sky.courceWork2.service.JavaQuestionService;

import java.util.Set;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

@ExtendWith(MockitoExtension.class)
public class JavaQuestionServiceTest {
    private final JavaQuestionService javaQuestionService = new JavaQuestionService();

    @ParameterizedTest
    @MethodSource("question1")
    public void addTest1(Question question) {
        javaQuestionService.add(question);
        assertThatExceptionOfType(QuestionAlreadyExistException.class)
                .isThrownBy(() -> javaQuestionService.add(new Question(question.getQuestion(), question.getAnswer())));
        assertThat(javaQuestionService.getAll()).containsExactlyInAnyOrder(question);
    }

    @ParameterizedTest
    @MethodSource("question2")
    public void addTest2(String question, String answer) {
        Question q = new Question(question, answer);
        javaQuestionService.add(question, answer);
        assertThatExceptionOfType(QuestionAlreadyExistException.class)
                .isThrownBy(() -> javaQuestionService.add(question, answer));
        assertThat(javaQuestionService.getAll()).containsExactlyInAnyOrder(q);

    }

    @Test
    public void removeTest() {
        javaQuestionService.add(new Question("test", "test"));
        javaQuestionService.remove(new Question("test", "test"));
        assertThatExceptionOfType(QuestionNotFoundException.class).isThrownBy(() -> javaQuestionService.remove(new Question("test", "test")));
    }

    @ParameterizedTest
    @MethodSource("questions")
    public void getRandomQuestionTest(Set<Question> question) {
        question.forEach(javaQuestionService::add);
        assertThat(javaQuestionService.getRandomQuestion()).isIn(javaQuestionService.getAll());

    }

    public static Stream<Arguments> question1() {
        return Stream.of(
                Arguments.of(new Question("Question", "Answer"))
        );
    }

    public static Stream<Arguments> question2() {
        return Stream.of(
                Arguments.of("Question", "Answer")
        );
    }

    public static Stream<Arguments> questions() {
        return Stream.of(
                Arguments.of(
                        Set.of(
                                new Question("Question1", "Answer1"),
                                new Question("Question2", "Answer2"),
                                new Question("Question3", "Answer3")
                        )
                )
        );
    }
}
