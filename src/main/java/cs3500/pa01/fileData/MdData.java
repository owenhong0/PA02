package cs3500.pa01.fileData;

import cs3500.pa01.questionWriting.Difficulty;
import cs3500.pa01.questionWriting.Question;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Represents a parsed Md file and its data
 */
public class MdData extends File {
  private final String fileName;
  private final Path filePath;
  private Date createDate;
  private final Date modifiedDate;
  private final BasicFileAttributes fileAttributes;
  private final ArrayList<String> content = new ArrayList<>();
  private Scanner scanner;
  private final HashMap<String, String> questionsAndAnswers = new HashMap<>();

  /**
   * Constructor for MdData method
   *
   * @param pathname path to this file as a string
   * @param truePath  path to this file as a path
   * @param attrs attributes to this class
   * @param create date created for this class
   * @param mod date modified for this class
   */
  public MdData(String pathname, Path truePath, BasicFileAttributes attrs, Date create, Date mod) {
    super(pathname);

    filePath = truePath;
    fileName = pathname;
    fileAttributes = attrs;
    createDate = create;
    modifiedDate = mod;
    initScanner();
  }

  private void initScanner() {
    try {
      scanner = new Scanner(filePath.toFile());
    } catch (IOException exception) {
      exception.printStackTrace();
    }
  }


  /**
   * populates all of the data by reading a file
   */
  private void populateData() {
    initScanner();
    while (scanner.hasNext()) {
      String nextLine = scanner.nextLine();
      if (nextLine.contains("#")) {
        content.add("\n" + "\n" + nextLine);
      }
      while (nextLine.contains("[[") && nextLine.contains("]]") && !nextLine.contains(":::")) {
        String impNote = nextLine.substring(nextLine.indexOf("[[") + 2, nextLine.indexOf("]]"));
        nextLine = nextLine.substring(nextLine.indexOf("]]") + 2);
        content.add("\n - " + impNote);
      }

    }
    content.add("\n");
  }

  private void populateQuestions() {
    while (scanner.hasNext()) {
      String nextLine = scanner.nextLine();
      if (nextLine.contains(":::")) {
        String question = nextLine.substring(nextLine.indexOf("[[") + 2,
            nextLine.indexOf(":"));
        String answer = nextLine.substring(nextLine.lastIndexOf(":") + 1,
            nextLine.lastIndexOf("]]"));
        questionsAndAnswers.put(question, answer);
      }
    }
  }

  /**
   * Sorts through all of the content in a Md File
   *
   * @return a single string with string commands that is the content of a md file
   */
  public String toString() {
    populateData();
    StringBuilder builder = new StringBuilder();
    for (String s : content) {
      builder.append(s); //TODO fix tests for this refactoring
    }
    return builder.toString();
  }

  /**
   * gets the modified date of an Md file
   *
   * @return the modified date field
   */
  public Date getModified() {
    return modifiedDate;
  }

  /**
   * returns file name of an Md file
   *
   * @return name of the file
   */
  public String getFileName() {
    return fileName;
  }

  /**
   * returns the file attributes of a file
   *
   * @return the file attributes
   */
  public BasicFileAttributes getFileAttributes() {
    return fileAttributes;
  }

  /**
   * gets the questions and answers of a file
   *
   * @return a string hashmap of questions and answers to later convert to questions
   */
  public ArrayList<Question> getQuestionsAndAnswers() {
    populateQuestions();
    ArrayList<Question> result = new ArrayList<>();
    for (String question : questionsAndAnswers.keySet()) {
      String answer = questionsAndAnswers.get(question);
      Question tempQ = new Question(question, answer, Difficulty.HARD);
      result.add(tempQ);
      //TODO remove the initConditions method as each are set to hard here
    }
    return result;
  }

}
