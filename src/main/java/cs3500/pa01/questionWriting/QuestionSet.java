package cs3500.pa01.questionWriting;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * represents a set of questions
 */
public class QuestionSet {
  private final ArrayList<Question> finalizedLoadedSet = new ArrayList<>();
  private final Path srFile;

  /**
   * constructs a question set object based on a given path
   *
   * @param filePath a given filepath to the srfile
   */
  public QuestionSet(Path filePath) {
    srFile = filePath;
  }

  /**
   * creates a new question based on information read from an sr file
   *
   * @param qa question and answer formatted in a string from the sr
   * @param diff the difficulty of the question in the form of a string
   * @return a question object
   */
  private Question generateQuestion(String qa, String diff) {
    Difficulty level;
    if (diff.equals("HARD")) {
      level = Difficulty.HARD;
    } else {
      level = Difficulty.EASY;
    }
    String question = qa.substring(0, qa.indexOf(":::")).trim();
    String answer = qa.substring(qa.lastIndexOf(":") + 1).trim();
    return new Question(question, answer, level);
  }

  /**
   * reads the srFile and adds questions to a set
   */
  public void readFile() {
    Scanner scanner = null;
    try {
      scanner = new Scanner(srFile);
    } catch (IOException ex) {
      ex.printStackTrace();
    }

    while (scanner.hasNext()) {
      String currLine = scanner.nextLine();
      if (currLine.equals(Difficulty.HARD.toString())
          || currLine.equals(Difficulty.EASY.toString())) {
        finalizedLoadedSet.add(generateQuestion(scanner.nextLine(), currLine));
      }
    }
  }

  /**
   * gets the loaded set of questions from an sr file
   *
   * @return an arraylist of questions
   */
  public ArrayList<Question> getFinalizedSet() {
    return finalizedLoadedSet;
  }
}
