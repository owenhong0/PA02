package cs3500.pa02.controller;

import cs3500.pa01.questionWriting.Question;

/**
 * represents the study session controller interface
 */
public interface Controller {
  /**
   * runs a study session by calling both views and model
   * while handling user input
   */
  void runStudySession();

  /**
   * checks the user input and processes appropriately
   *
   * @param user input as a string
   * @param curr the current question
   * @param answer the answer of the current question
   * @return a boolean indicating whether to move onto the next question or not
   */
  boolean checkStep(String user, Question curr, String answer);
}
