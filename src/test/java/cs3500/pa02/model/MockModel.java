package cs3500.pa02.model;

import cs3500.pa01.questionWriting.Question;

/**
 * represents a mock class of the model interface for testing
 */
public class MockModel implements Model {

  /**
   * updates the model for a question switching to easy
   *
   * @param q a question object
   */
  @Override
  public void toEasy(Question q) {

  }

  /**
   * updates the model for a question switching to hard
   *
   * @param curr a question object
   */
  @Override
  public void toHard(Question curr) {

  }

  /**
   * generates the final list of questions (mixed with hard and easy) based on how many the user
   * wants to study
   *
   * @param sessionLength a number of how many questions to study
   */
  @Override
  public void generateFinalizedQuestionList(int sessionLength) {

  }

  /**
   * helps return the next question in the final list of questions and makes sure it's not a repeat
   *
   * @return a question object that represents the next question
   */
  @Override
  public Question getNextQuestion() {
    return null;
  }

  /**
   * returns the answer of a given question
   *
   * @param q a given question
   * @return a string of the answer
   */
  @Override
  public String getAnswer(Question q) {
    return null;
  }

  /**
   * updates the sr file by writing newly changed questions to it
   */
  @Override
  public void updateSr() {

  }

  /**
   * gets the stats of a user's session
   *
   * @return a string of the statistics of a study session
   */
  @Override
  public String getStats() {
    return null;
  }
}
