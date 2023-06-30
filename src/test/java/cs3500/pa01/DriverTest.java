package cs3500.pa01;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import cs3500.pa01.fileData.Flag;
import java.nio.file.Path;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DriverTest {
  @Test
  public void testReadAndWriteFile() {

    Path startDir = Path.of("src/test/resources/{input}");
    String orderFlag = "created";
    String orderFlag2 = "filename";
    String orderFlag3 = "modified";
    Path destination = Path.of("src/test/resources/{output}/testStudyGuide.md");
    Driver.readAndWriteFile(startDir, orderFlag, destination);
    Driver.readAndWriteFile(startDir, orderFlag2, destination);
    Driver.readAndWriteFile(startDir, orderFlag3, destination);
    Path srFile = Path.of("src/test/resources/{output}/testStudyGuide.sr");
    Assertions.assertTrue(destination.toFile().exists());
    Assertions.assertTrue(srFile.toFile().exists());
  }

  @Test
  void processFlag() {
    assertEquals(Flag.FILENAME, Driver.processFlag("filename"));
    assertEquals(Flag.CREATED, Driver.processFlag("created"));
    assertEquals(Flag.MODIFIED, Driver.processFlag("modified"));
  }

  @Test
  void runStudy() {
    assertThrows(NoSuchElementException.class, () -> Driver.runStudy());
  }
}