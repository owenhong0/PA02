package cs3500.pa01;

import cs3500.pa01.fileData.Flag;
import cs3500.pa01.fileData.MdProcessor;
import cs3500.pa02.controller.StudySessionViewController;
import cs3500.pa02.model.Model;
import cs3500.pa02.model.StudySessionModel;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.util.Objects;
import java.util.Scanner;

/**
 * This is the main driver of this project.
 */
public class Driver {
  /**
   * Project entry point
   *
   * @param args - no command line args required
   */
  public static void main(String[] args) {

    if (args.length == 0) {
      runStudy();
    } else {
      readAndWriteFile(Path.of(args[0]), args[1], Path.of(args[2]));
    }
  }

  /**
   * this calls the reader and writer and writes the study guide
   *
   * @param startDir the starting directory to start the filevisitor
   * @param orderFlag the ordering flag that should order the list of md files
   * @param destination the path of the studyguide file that should be written
   */
  public static void readAndWriteFile(Path startDir, String orderFlag, Path destination) {
    Path inputDir = Objects.requireNonNull(startDir);
    String flag = Objects.requireNonNull(orderFlag);
    Path destDir = Objects.requireNonNull(destination);

    MdProcessor processor = new MdProcessor(inputDir, processFlag(flag), destDir);
    processor.writeStudyGuide();
    processor.writeSr();
  }

  /**
   * determines which flag to sort the files based on user input
   *
   * @param order flag as a string
   * @return a flag object
   */
  public static Flag processFlag(String order) {
    Flag orderingFlag;

    if (order.equals("filename")) {
      orderingFlag = Flag.FILENAME;
    } else if (order.equals("created")) {
      orderingFlag = Flag.CREATED;
    } else {
      orderingFlag = Flag.MODIFIED;
    }
    return orderingFlag;
  }

  /**
   * this method kicks off the study session by calling the controller
   * and passing in appropriate arguments
   */
  public static void runStudy() {
    System.out.println("Please enter the filepath of the designated sr file");
    Scanner scanner = new Scanner(System.in);
    Readable input = new InputStreamReader(System.in);
    Model model = new StudySessionModel(Path.of(scanner.next()));
    Appendable output = System.out;
    StudySessionViewController s = new StudySessionViewController(input,
        output, model);
    s.runStudySession();
  }
}