package cs3500.pa02.controller;

import cs3500.pa01.questionWriting.Difficulty;
import cs3500.pa01.questionWriting.Question;
import cs3500.pa02.model.Model;
import cs3500.pa02.view.StudySessionView;
import cs3500.pa02.view.View;

/**
 * represents the controller for a study session
 */
public class StudySessionViewController implements Controller {
  private final Model model;
  private final View view;
  private final Readable input;
  private Appendable output;

  /**
   * creates a view controller based on given input, output, models
   *
   * @param reader given input stream
   * @param out given output stream
   * @param m given model
   */
  public StudySessionViewController(Readable reader, Appendable out, Model m) {
    model = m;
    input = reader;
    output = out;
    view = new StudySessionView(output);
  }

  /**
   * processes the user's input and determines what to do after a question has
   * been presented
   *
   * @param user input as a string
   * @param curr the current question
   * @param answer the answer of the current question
   * @return whether or not the next question can be displayed
   */
  public boolean checkStep(String user, Question curr, String answer) {
    boolean canContinue = false;
    if (user.equalsIgnoreCase(Difficulty.EASY.toString())
        && curr.getType().toString().equalsIgnoreCase(Difficulty.HARD.toString())) {
      model.toEasy(curr);
      canContinue = true;
    } else if (user.equalsIgnoreCase(Difficulty.HARD.toString())
        && curr.getType().toString().equalsIgnoreCase(Difficulty.EASY.toString())) {
      model.toHard(curr);
      canContinue = true;
    } else if (user.equalsIgnoreCase("answer")) {
      view.printAnswer(answer);
    } else if (user.equalsIgnoreCase(curr.getType().toString())) {
      canContinue = true;
    }

    return canContinue;
  }

  /**
   * stars the main loop of a study session and delegates actions based on user input
   */
  public void runStudySession() {
    int sessionLength = view.printWelcome(input);
    model.generateFinalizedQuestionList(sessionLength);
    while (sessionLength > 0) {
      Question q = model.getNextQuestion();
      String answer = model.getAnswer(q);
      view.printNextQuestion(q);
      String userResponse = view.printPostPrompt(input);
      boolean go = checkStep(userResponse, q, answer);
      while (!go) {
        String newResponse = view.printPostPrompt(input);
        go = checkStep(newResponse, q, answer);
      }

      sessionLength--;
    }
    model.updateSr();

    view.printStats(model.getStats());
  }
}
