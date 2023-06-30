package cs3500.pa01.questionWriting;

/**
 * represents a Question
 */
public class Question {
  private final String question;
  private final String answer;
  private Difficulty difficulty;

  /**
   *
   * @param q is the question
   * @param a is the answer
   * @param level is the difficulty of the question
   */
  public Question(String q, String a, Difficulty level) {
    question = q;
    answer = a;
    difficulty = level;
  }

  /**
   * returns the question and answer of a Question class
   *
   * @return the question and answer as a string
   */
  public String toString() {
    return question + " ::: " + answer;
  }

  /**
   * gets the question of a question object
   *
   * @return the string question
   */
  public String getQuestion() {
    return  question;
  }

  /**
   * returns the answer of a Question class
   *
   * @return the answer field
   */
  public String getAnswer() {
    return answer;
  }

  /**
   * gets the current difficulty level of this question
   *
   * @return an enumeration of the difficulty
   */
  public Difficulty getType() {
    return difficulty;
  }

  /**
   * changes the difficulty of a question
   *
   * @param e an Enumeration of either Hard or Easy
   */
  public void setType(Difficulty e) {
    difficulty = e;
  }
}
