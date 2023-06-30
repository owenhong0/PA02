package cs3500.pa01.comparators;

import cs3500.pa01.fileData.MdData;
import java.util.Comparator;

/**
 * represents the comparator class to sort MdData by last modified date
 */
public class OrderByModified implements Comparator<MdData> {
  /**
   * comparing two files by their last modified date
   *
   * @param o1 the first object to be compared.
   * @param o2 the second object to be compared.
   * @return an integer to indicate whether a date comes before or after the other date
   */
  @Override
  public int compare(MdData o1, MdData o2) {
    return o1.getModified().compareTo(o2.getModified());
  }
}
