package cs3500.pa02.view;

import cs3500.pa01.questionWriting.Question;

/**
 * represent the interface for the study session view
 */
public interface View {

  /**
   * displays the given question's answer to the user
   *
   * @param answer the answer in the form of a string
   */
  void printAnswer(String answer);

  /**
   * displays the welcome message to the user
   *
   * @param input the input stream to get the user's response
   * @return the number of questions the user wants to study
   */
  int printWelcome(Readable input);

  /**
   * displays the next question to the user
   *
   * @param q the question object to be displayed
   */
  void printNextQuestion(Question q);

  /**
   * displays a series of options for the user after a question
   *
   * @param input the input stream for the user's input
   * @return the user's input in the form of a string
   */
  String printPostPrompt(Readable input);

  /**
   * displays the stats of a study session to the user
   *
   * @param stats in the form of a string the statistics
   */
  void printStats(String stats);
}
