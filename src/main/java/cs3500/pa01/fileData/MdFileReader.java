package cs3500.pa01.fileData;

import static java.nio.file.FileVisitResult.CONTINUE;

import cs3500.pa01.questionWriting.Question;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Date;

/**
 * represents a filevisitor for the directory of md files
 */
public class MdFileReader implements FileVisitor<Path> {
  private final ArrayList<MdData> listMd = new ArrayList<>();
  private final ArrayList<ArrayList<Question>> listQ = new ArrayList<>();

  /**
   *  continues before reading a directory
   *  and setting the callback boolean true
   *
   * @param dir
   *          a reference to the directory
   * @param attrs
   *          the directory's basic attributes
   *
   * @return the continue enumeration
   */
  @Override
  public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
    return CONTINUE;
  }

  /**
   * when a file is read, a new mdData file is taken and passed
   * the information in an Md file to further process
   *
   * @param file
   *          a reference to the file
   * @param attrs
   *          the file's basic attributes
   *
   * @return a continue enumeration to continue reading the next file
   * @throws IOException the exception for when a file cannot be read
   */
  @Override
  public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
    String fileName = file.getFileName().toString();
    if (fileName.endsWith(".md")) {
      MdData newFile = new MdData(fileName, file, attrs,
          new Date(System.currentTimeMillis()),
          new Date(attrs.lastModifiedTime().toMillis()));
      listQ.add(newFile.getQuestionsAndAnswers());
      listMd.add(newFile);
    }
    return CONTINUE;
  }

  /**
   * when a file is failed to be visited, keep visiting files
   *
   * @param file
   *          a reference to the file
   * @param exc
   *          the I/O exception that prevented the file from being visited
   *
   * @return the continue enumeration to keep reading files
   * @throws IOException the exception for when this method cannot be processed
   */
  @Override
  public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
    visitFailedCalled();
    return CONTINUE;
  }

  /**
   * tester for the visitFilecalled function
   *
   * @return true that the function works as intended, no need to handle anything
   */
  public boolean visitFailedCalled() {
    return true;
  }

  /**
   * after visiting a directory of files, keep
   *
   * @param dir
   *          a reference to the directory
   * @param exc
   *          {@code null} if the iteration of the directory completes without
   *          an error; otherwise the I/O exception that caused the iteration
   *          of the directory to complete prematurely
   *
   * @return the enumeration true to keep reading files
   * @throws IOException the exception for when this method cannot be called correctly
   */
  @Override
  public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
    return CONTINUE;
  }

  /**
   * gets the list of md files read
   *
   * @return an arraylist of read files
   */
  public ArrayList<MdData> getList() {
    return listMd; //TODO fix the tests
  }

  /**
   * gets the list of list questions from a list of md files
   *
   * @return a 2d arraylist of questions
   */
  public ArrayList<ArrayList<Question>> getQuestAndAnswer() {
    return listQ;
  }
}
