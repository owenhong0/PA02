package cs3500.pa02.view;

import cs3500.pa01.questionWriting.Question;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

/**
 * represents the view for a study session
 */
public class StudySessionView implements View {
  private final Appendable output;
  private Scanner scanner;

  /**
   * creates a view based on a given output stream
   *
   * @param out an output stream
   */
  public StudySessionView(Appendable out) {
    output = Objects.requireNonNull(out);
  }

  /**
   * prints the next question's question to the console
   *
   * @param q a question object
   */
  public void printNextQuestion(Question q) {
    try {
      output.append(q.getQuestion()).append("\n"); // this may fail, hence the try-catch
    } catch (IOException e) {
      throw new RuntimeException(e.getMessage());
    }
  }

  /**
   * prints the answer of a given question
   *
   * @param s the answer as a string
   */
  public void printAnswer(String s) {
    try {
      output.append("The Answer is: ").append(s).append("\n"); // this may fail, hence the try-catch
    } catch (IOException e) {
      throw new RuntimeException(e.getMessage());
    }
  }

  /**
   * prints a welcome message to the user
   *
   * @param in a stream input readable
   * @return the number of questions the user wants to study
   */
  public int printWelcome(Readable in) {
    StringBuilder builder = new StringBuilder("Welcome to a new study session,"
        + " how many questions would you like to study?\n");
    scanner = new Scanner(in);

    try {
      output.append(builder);
    } catch (IOException e) {
      throw new RuntimeException(e.getMessage());
    }

    int result = 0;
    try {
      result = Integer.parseInt(scanner.next());
    } catch (NumberFormatException e) {
      e.printStackTrace();
    }
    return result;
  }

  /**
   * presents user with two options to classify a question and record their response
   *
   * @return a string of the user's response
   */
  public String printPostPrompt(Readable in) {
    scanner = new Scanner(in);
    StringBuilder builder =
        new StringBuilder("Was this question hard or easy? Type in 'easy' or 'hard' to "
            + "continue or 'answer' to see the answer\n");

    try {
      output.append(builder);
    } catch (IOException e) {
      throw new RuntimeException(e.getMessage());
    }

    StringBuilder response = new StringBuilder(scanner.next());
    return response.toString();
  }

  /**
   * prints the statistics of a study session
   *
   * @param stats is the model's string of stats
   */
  public void printStats(String stats) {
    try {
      output.append(stats);
    } catch (IOException e) {
      throw new RuntimeException(e.getMessage());
    }
  }
}
