package cs3500.pa01.fileData;

import cs3500.pa01.comparators.OrderByCreated;
import cs3500.pa01.comparators.OrderByFileName;
import cs3500.pa01.comparators.OrderByModified;
import cs3500.pa01.questionWriting.StudySessionWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

/**
 * represents a class to write to the StudyGuide file
 */
public class MdProcessor {
  private final Path startDir;
  private final Path destinationDir;
  private final Flag flag;

  /**
   * constructs a processor based on given filepath flag and path to studyguide
   *
   * @param filePath path to md directory
   * @param f        flag to order files
   * @param dest     path to studyguide
   */
  public MdProcessor(Path filePath, Flag f, Path dest) {
    startDir = filePath;
    flag = f;
    destinationDir = dest;
  }

  /**
   * gets the sorted list of md files to write to the study guide
   *
   * @param flag      the ordering flag
   * @param inputList the finalized list of md files
   * @return an arraylist of md files
   */
  private ArrayList<MdData> applySort(Flag flag, ArrayList<MdData> inputList) {
    if (flag.equals(Flag.FILENAME)) { //TODO change these to custom enum
      inputList.sort(new OrderByFileName());
    } else if (flag.equals(Flag.CREATED)) {
      inputList.sort(new OrderByCreated());
    } else {
      inputList.sort(new OrderByModified());
    } //TODO make sure this is called in the new writer function and make tests
    return inputList;
  }

  /**
   * gets the finalized sorted list of md files
   *
   * @return an arraylist of md files
   */
  private ArrayList<MdData> prepareGuideList() {
    MdFileReader fileReader = new MdFileReader(); //TODO make tests for this

    try {
      Files.walkFileTree(startDir, fileReader);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return applySort(flag, fileReader.getList());
  }

  /**
   * writes the sr file using the list of questions from the file reader
   */
  public void writeSr() {
    MdFileReader fileReader = new MdFileReader();

    try {
      Files.walkFileTree(startDir, fileReader);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    String srFile = destinationDir.toString().substring(0,
        destinationDir.toString().indexOf("md")) + "sr";
    StudySessionWriter studySessionWriter =
        new StudySessionWriter(Path.of(srFile), fileReader.getQuestAndAnswer());
    studySessionWriter.writeQuestions();
  }

  /**
   * writes the study guide with information in each md file stored
   */
  public void writeStudyGuide() {
    try {
      FileWriter fileWriter = new FileWriter(destinationDir.toString());
      for (MdData file : prepareGuideList()) {
        fileWriter.write(file.toString());
      }
      fileWriter.close();
    } catch (Exception e) {
      e.printStackTrace();
      System.err.println("The file writing process failed " + e);
    }
  }

}
