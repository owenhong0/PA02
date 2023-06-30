package cs3500.pa02.view;

import java.io.IOException;

/**
 * Represents a mock class for the appendable class
 */
public class MockAppendable implements Appendable {

  private void throwInOut() throws IOException {
    throw new IOException("Mock throwing an error");
  }

  @Override
  public Appendable append(CharSequence csq) throws IOException {
    throwInOut();
    return null;
  }

  @Override
  public Appendable append(CharSequence csq, int start, int end) throws IOException {
    throwInOut();
    return null;
  }

  @Override
  public Appendable append(char c) throws IOException {
    throwInOut();
    return null;
  }
}
