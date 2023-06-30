package cs3500.pa01.comparators;

import cs3500.pa01.fileData.MdData;
import java.util.Comparator;

/**
 * Comparator class to sort md data files by date created
 */
public class OrderByCreated implements Comparator<MdData> {

  /**
   * compare the two creation dates for MdData to sort
   *
   * @param o1 the first object to be compared.
   * @param o2 the second object to be compared.
   * @return an integer to indicate the order of creation dates
   */
  @Override
  public int compare(MdData o1, MdData o2) {
    return o1.getFileAttributes().creationTime().compareTo(o2.getFileAttributes().creationTime());
  }
}
