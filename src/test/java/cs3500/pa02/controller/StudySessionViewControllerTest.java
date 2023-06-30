package cs3500.pa02.controller;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cs3500.pa01.questionWriting.Difficulty;
import cs3500.pa01.questionWriting.Question;
import cs3500.pa02.model.MockModel;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.file.Path;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StudySessionViewControllerTest {
  private StudySessionViewController controller;
  private final Question q1 = new Question("Question 1", "Answer 1", Difficulty.HARD);
  private final Question q2 = new Question("Question 2", "Answer 2", Difficulty.EASY);
  private final Appendable writer = new StringWriter();
  private final MockModel mockModel = new MockModel();

  @BeforeEach
  public void setup() {
    Readable easy = new StringReader("4");
    controller = new StudySessionViewController(easy, writer, mockModel);
  }

  @Test
  void checkStep() {
    assertTrue(controller.checkStep("easy", q1, q1.getAnswer()));
    assertTrue(controller.checkStep("hard", q2, q2.getAnswer()));
    assertTrue(controller.checkStep("easy", q2, q2.getAnswer()));
    assertFalse(controller.checkStep("answer", q2, q2.getAnswer()));
  }

  @Test
  void runStudySession() {
    assertThrows(NullPointerException.class, () -> controller.runStudySession());
  }
}