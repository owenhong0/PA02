package cs3500.pa01.questionWriting;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.ArrayList;

/**
 * represents the class for writing the sr file
 */
public class StudySessionWriter {
  private final String srPath;
  private final ArrayList<ArrayList<Question>> questionToWrite;

  /**
   * creates a writer based on the srfile path and questions from md files
   *
   * @param filePath given filepath to sr file
   * @param inpQ given questions in the form of a 2d arraylist
   */
  public StudySessionWriter(Path filePath, ArrayList<ArrayList<Question>> inpQ) {
    questionToWrite = inpQ;
    srPath = filePath.toString();
  }

  /**
   * writes a given 2d array of questions to the sr file
   * and wipes it before writing
   */
  public void writeQuestions() {
    FileWriter fileWriter;
    PrintWriter printWriter;
    try {
      fileWriter = new FileWriter(srPath);
      printWriter = new PrintWriter(fileWriter);
      printWriter.flush();
      for (ArrayList<Question> rowOfQuestion : questionToWrite) {
        for (Question q : rowOfQuestion) {
          fileWriter.write(q.getType().toString() + "\n");
          fileWriter.write(q + "\n");
        }
      }
      fileWriter.close();
    } catch (Exception e) {
      e.printStackTrace();
      System.err.println("The file writing process failed " + e);
    }

  }
}
