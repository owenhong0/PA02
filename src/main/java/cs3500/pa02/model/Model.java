package cs3500.pa02.model;

import cs3500.pa01.questionWriting.Question;

/**
 * represents the interface for the study session model
 */
public interface Model {
  /**
   * sets the status of a question in the list to Easy
   *
   * @param q a question object
   */
  void toEasy(Question q);

  /**
   * sets the difficulty of a question in the list to Hard
   *
   * @param curr a question object
   */
  void toHard(Question curr);

  /**
   * generates the final list of questions (mixed with hard and easy) based on how many the user
   * wants to study
   *
   * @param sessionLength a number of how many questions to study
   */
  void generateFinalizedQuestionList(int sessionLength);

  /**
   * helps return the next question in the final list of questions and makes sure it's not a repeat
   *
   * @return a question object that represents the next question
   */
  Question getNextQuestion();

  /**
   * returns the answer of a given question
   *
   * @param q a given question
   * @return a string of the answer
   */
  String getAnswer(Question q);

  /**
   * updates the sr file by writing newly changed questions to it
   */
  void updateSr();

  /**
   * gets the stats of a study session
   *
   * @return the string of all the stats
   */
  String getStats();
}
