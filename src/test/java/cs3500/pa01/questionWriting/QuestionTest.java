package cs3500.pa01.questionWriting;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * represents the tests for the question class
 */
class QuestionTest {
  private Question q1;
  private Question q2;

  @BeforeEach
  public void setup() {
    q1 = new Question("Question 1", "Answer 1", Difficulty.EASY);
    q2 = new Question("Question 2", "Answer 2", Difficulty.HARD);
  }

  /**
   * checks tests for the toString method
   */
  @Test
  void testToString() {
    assertEquals("Question 1 ::: Answer 1", q1.toString());
    assertEquals("Question 2 ::: Answer 2", q2.toString());
  }

  /**
   * checks tests for the getQuestions method
   */
  @Test
  void getQuestion() {
    assertEquals("Question 1", q1.getQuestion());
    assertEquals("Question 2", q2.getQuestion());
  }

  /**
   * checks tests for the getAnswer method
   */
  @Test
  void getAnswer() {
    assertEquals("Answer 1", q1.getAnswer());
    assertEquals("Answer 2", q2.getAnswer());
  }

  /**
   * checks tests for the getType method
   */
  @Test
  void getType() {
    assertEquals(Difficulty.EASY, q1.getType());
    assertEquals(Difficulty.HARD, q2.getType());
  }

  /**
   * checks tests for the setType method
   */
  @Test
  void setType() {
    assertEquals(Difficulty.EASY, q1.getType());
    q1.setType(Difficulty.HARD);
    assertEquals(Difficulty.HARD, q1.getType());
    assertEquals(Difficulty.HARD, q2.getType());
    q2.setType(Difficulty.EASY);
    assertEquals(Difficulty.EASY, q2.getType());
  }
}