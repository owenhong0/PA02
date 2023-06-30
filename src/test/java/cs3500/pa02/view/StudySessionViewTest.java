package cs3500.pa02.view;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import cs3500.pa01.questionWriting.Difficulty;
import cs3500.pa01.questionWriting.Question;
import cs3500.pa02.model.StudySessionModel;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.file.Path;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * represents the tests for the study session view
 */
class StudySessionViewTest {
  private StudySessionView view;
  private StudySessionView mockView;
  private StringWriter writer;
  private Question q1;

  @BeforeEach
  public void setUp() {
    q1 = new Question("Question 1", "Answer 1", Difficulty.EASY);
    writer = new StringWriter();
    view = new StudySessionView(writer);
    MockAppendable mockAppendable = new MockAppendable();
    mockView = new StudySessionView(mockAppendable);
  }

  /**
   * checks tests for the printNextQuestion method
   */
  @Test
  void printNextQuestion() {
    view.printNextQuestion(q1);
    assertEquals("Question 1\n", writer.toString());
    assertThrows(RuntimeException.class, () -> mockView.printNextQuestion(q1));
  }

  /**
   * checks tests for the printAnswer method
   */
  @Test
  void printAnswer() {
    view.printAnswer(q1.getAnswer());
    assertEquals("The Answer is: Answer 1\n", writer.toString());
    assertThrows(RuntimeException.class, () -> mockView.printAnswer(q1.getAnswer()));
  }

  /**
   * checks tests for the printWelcome method
   */
  @Test
  void printWelcome() {
    int result = view.printWelcome(new StringReader("5"));
    assertEquals(5, result);
    assertThrows(RuntimeException.class, () -> mockView.printWelcome(new StringReader("5")));
  }

  /**
   * checks tests for the printPostQPrompt method
   */
  @Test
  void printPostPrompt() {
    view.printPostPrompt(new StringReader("answer"));
    assertEquals("Was this question hard or easy? Type in 'easy' or"
        + " 'hard' to continue or 'answer' to see the answer\n", writer.toString());
    assertThrows(RuntimeException.class,
        () -> mockView.printPostPrompt(new StringReader("answer")));
  }

  /**
   * checks tests for the printStats method
   */
  @Test
  void printStats() {
    StudySessionModel model
        = new StudySessionModel(Path.of("src/test/resources/{output}/testSr.sr"));
    view.printStats(model.getStats());
    assertEquals("Nice! you studied " + 0 + " questions out of " + 0
        + ". " + 0 + " of these questions went from Hard to Easy and "
        + 0 + " of these questions went from Easy to Hard."
        + " There are now " + 0 + " hard questions and " + 0
        + " easy questions", writer.toString());
    assertThrows(RuntimeException.class, () -> mockView.printStats(model.getStats()));
  }
}