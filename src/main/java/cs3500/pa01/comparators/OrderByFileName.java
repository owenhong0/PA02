package cs3500.pa01.comparators;

import cs3500.pa01.fileData.MdData;
import java.util.Comparator;

/**
 * Comparator class to sort the order of md data files by filename
 */
public class OrderByFileName implements Comparator<MdData> {
  /**
   *
   * @param fileName1 the first object to be compared.
   * @param fileName2 the second object to be compared.
   * @return an integer to indicate the order between two filenames
   */
  @Override
  public int compare(MdData fileName1, MdData fileName2) {
    return fileName1.getFileName().compareTo(fileName2.getFileName());
  }
}
