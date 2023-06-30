package cs3500.pa02.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cs3500.pa01.questionWriting.Difficulty;
import cs3500.pa01.questionWriting.Question;
import java.nio.file.Path;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * represents tests for the study session model
 */
class StudySessionModelTest {
  private StudySessionModel model;
  private StudySessionModel model2;
  private StudySessionModel model3;
  private StudySessionModel model4;
  private Question q1;
  private Question q2;
  private Question q3;
  private Question q4;

  @BeforeEach
  public void setup() {
    q1 = new Question("Question 1?", "Answer 1", Difficulty.HARD);
    q2 = new Question("Question 2?", "Answer 2", Difficulty.EASY);
    q3 = new Question("Question 3?", "Answer 3", Difficulty.HARD);
    q4 = new Question("Question 4?", "Answer 4", Difficulty.EASY);
    model = new StudySessionModel(Path.of("src/test/resources/{output}/testSr.sr"));
    model2 = new StudySessionModel(Path.of("src/test/resources/{output}/testSr2.sr"));
    model3 = new StudySessionModel(Path.of("src/test/resources/{output}/testSr3.sr"));
    model4 = new StudySessionModel(Path.of("src/test/resources/{output}/testSr4.sr"));
  }

  /**
   * checks tests for the generateFinalizedQuestionList method
   */
  @Test
  void generateFinalizedQuestionList() {
    ArrayList<Question> emptyList = new ArrayList<>();
    assertEquals(emptyList, model.getQuestionsToAsk());
    model.generateFinalizedQuestionList(5);
    emptyList.add(q1);
    emptyList.add(q2);
    emptyList.add(q3);
    emptyList.add(q4);
    for (int i = 0; i < emptyList.size() - 1; i++) {
      assertEquals(emptyList.get(i).getQuestion(), model.getQuestionsToAsk().get(i).getQuestion());
      assertEquals(emptyList.get(i).getAnswer(), model.getQuestionsToAsk().get(i).getAnswer());
      assertEquals(emptyList.get(i).getType(), model.getQuestionsToAsk().get(i).getType());
    }

    model2.generateFinalizedQuestionList(4);
    emptyList.get(1).setType(Difficulty.HARD);
    emptyList.get(3).setType(Difficulty.HARD);
    for (int i = 0; i < emptyList.size() - 1; i++) {
      assertEquals(emptyList.get(i).getType(), model2.getQuestionsToAsk().get(i).getType());
    }


    model3.generateFinalizedQuestionList(4);
    for (int i = 0; i < emptyList.size() - 1; i++) {
      emptyList.get(i).setType(Difficulty.EASY);
      assertEquals(emptyList.get(i).getType(), model3.getQuestionsToAsk().get(i).getType());
    }
  }

  /**
   * checks invalid tests for the generateFinalizedQuestionList method
   */
  @Test
  void testInvalidGenerate() {
    assertThrows(IllegalArgumentException.class,
        () -> model2.generateFinalizedQuestionList(0));
  }

  /**
   * checks tests for the getNextQuestion method
   */
  @Test
  void getNextQuestion() {
    model.generateFinalizedQuestionList(4);
    assertTrue(model.getQuestionsToAsk().contains(model.getNextQuestion()));
  }

  /**
   * checks invalid tests for the generateFinalizedQuestionList method
   */
  @Test
  void testGenerateFinalizedQuestionList() {
    assertThrows(IllegalArgumentException.class,
        () -> model.generateFinalizedQuestionList(-1));
  }

  /**
   * checks tests for the getNextQuestionFrom method
   */
  @Test
  void getNextQuestionFrom() {
    model.generateFinalizedQuestionList(5);
    assertTrue(model.getQuestionsToAsk().contains(
        model.getNextQuestionFrom(model.getQuestionsToAsk())));
  }

  /**
   * checks tests for the getAnswer method
   */
  @Test
  void getAnswer() {
    model.generateFinalizedQuestionList(5);
    assertEquals("Answer 1", model.getAnswer(model.getQuestionsToAsk().get(0)));
    assertEquals("Answer 2", model.getAnswer(model.getQuestionsToAsk().get(1)));
  }

  /**
   * checks tests for the toEasy method
   */
  @Test
  void toEasy() {
    model2.generateFinalizedQuestionList(4);
    ArrayList<Question> questions = model2.getQuestionsToAsk();

    assertEquals(Difficulty.HARD, questions.get(0).getType());
    model2.toEasy(questions.get(0));
    assertEquals(Difficulty.EASY, questions.get(0).getType());

    assertEquals(Difficulty.HARD, questions.get(2).getType());
    model2.toEasy(questions.get(2));
    assertEquals(Difficulty.EASY, questions.get(2).getType());
  }

  /**
   * checks tests for the toHard method
   */
  @Test
  void toHard() {
    model3.generateFinalizedQuestionList(4);
    ArrayList<Question> questions = model3.getQuestionsToAsk();

    assertEquals(Difficulty.EASY, questions.get(0).getType());
    model3.toHard(questions.get(0));
    assertEquals(Difficulty.HARD, questions.get(0).getType());

    assertEquals(Difficulty.EASY, questions.get(2).getType());
    model3.toHard(questions.get(2));
    assertEquals(Difficulty.HARD, questions.get(2).getType());
  }

  /**
   * checks tests for the getCounterEasy method
   */
  @Test
  void getCounterEasy() {
    model.generateFinalizedQuestionList(4);
    ArrayList<Question> questions = model.getQuestionsToAsk();

    model.toEasy(questions.get(0));
    model.toEasy(questions.get(1));
    model.toEasy(questions.get(2));

    assertEquals(3, model.getCounterEasy());
  }

  /**
   * checks tests for the getCounterHard method
   */
  @Test
  void getCounterHard() {
    model.generateFinalizedQuestionList(4);
    ArrayList<Question> questions = model.getQuestionsToAsk();

    model.toHard(questions.get(1));
    model.toHard(questions.get(3));

    assertEquals(2, model.getCounterHard());
  }

  /**
   * checks tests for the totalQuestions method
   */
  @Test
  void totalQuestions() {
    model.generateFinalizedQuestionList(4);
    assertEquals(4, model.totalQuestions());
  }

  /**
   * checks tests for the getAsked method
   */
  @Test
  void getAsked() {
    model.generateFinalizedQuestionList(4);
    model.getNextQuestion();
    model.getNextQuestion();
    assertEquals(2, model.getAsked());
  }

  /**
   * checks tests for the getFinalHard method
   */
  @Test
  void getFinalHard() {
    model.generateFinalizedQuestionList(4);
    ArrayList<Question> questions = model.getQuestionsToAsk();

    model.toHard(questions.get(1));
    model.toHard(questions.get(3));

    assertEquals(3, model.getFinalHard());
  }

  /**
   * checks tests for the getFinalEasy method
   */
  @Test
  void getFinalEasy() {
    model.generateFinalizedQuestionList(4);
    ArrayList<Question> questions = model.getQuestionsToAsk();

    model.toEasy(questions.get(0));
    model.toEasy(questions.get(1));
    model.toEasy(questions.get(2));

    assertEquals(4, model.getFinalEasy());
  }

  /**
   * checks tests for the getStats method
   */
  @Test
  void getStats() {
    String stats = "Nice! you studied " + 0 + " questions out of " + 0
        + ". " + 0 + " of these questions went from Hard to Easy and "
        + 0 + " of these questions went from Easy to Hard."
        + " There are now " + 0 + " hard questions and " + 0
        + " easy questions";
    assertEquals(stats, model.getStats());
  }

  /**
   * checks tests for the updateSr method
   */
  @Test
  void updateSr() {
    model4.generateFinalizedQuestionList(4);
    assertEquals(Difficulty.HARD, model4.getQuestionsToAsk().get(0).getType());

    model4.toEasy(model4.getQuestionsToAsk().get(0));
    model4.toEasy(model4.getQuestionsToAsk().get(1));

    model4.updateSr();
    StudySessionModel model5 =
        new StudySessionModel(Path.of("src/studySession.sr"));
    model5.generateFinalizedQuestionList(4);
    assertEquals(Difficulty.EASY, model5.getQuestionsToAsk().get(3).getType());
    assertEquals(Difficulty.EASY, model5.getQuestionsToAsk().get(2).getType());
    assertEquals(Difficulty.HARD, model5.getQuestionsToAsk().get(0).getType());
    assertEquals(Difficulty.HARD, model5.getQuestionsToAsk().get(1).getType());

    model4.toHard(model4.getQuestionsToAsk().get(0));
    model4.toHard(model4.getQuestionsToAsk().get(1));
    model4.updateSr();
  }
}