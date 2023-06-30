package cs3500.pa01.fileData;

import static org.junit.jupiter.api.Assertions.assertTrue;

import cs3500.pa01.questionWriting.Question;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

/**
 * represents tests for the MdData class
 */
class MdDataTest {

  @Test
  void getQuestionsAndAnswers() {
    MdFileReader reader = new MdFileReader();
    ArrayList<String> testList = new ArrayList<>();
    testList.add("What is the chemical symbol of hydrogen?");
    testList.add("What is the name of my roommate?");
    testList.add("Is this the last question?");
    testList.add("What is the capital of Massachusetts?");

    try {
      Files.walkFileTree(Path.of("sampleMd"), reader);
      // Figured out this does not work in BeforeAll which is what broke all of my PA01 tests
      MdData testFile = reader.getList().get(0);
      MdData testFile2 = reader.getList().get(1);
      for (Question q : testFile.getQuestionsAndAnswers()) {
        assertTrue(testList.contains(q.getQuestion()));
      }
      assertTrue(testFile2.toString().contains("An array is declared via the brackets"));
    } catch (IOException e) {
      e.printStackTrace();
    }

  }
}