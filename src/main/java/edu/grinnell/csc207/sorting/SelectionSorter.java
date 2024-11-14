package edu.grinnell.csc207.sorting;

import edu.grinnell.csc207.util.ArrayUtils;
import java.util.Comparator;

/**
 * Something that sorts using selection sort.
 *
 * @param <T> The types of values that are sorted.
 * @author Samuel A. Rebelsky
 */
public class SelectionSorter<T> implements Sorter<T> {
  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /** The way in which elements are ordered. */
  Comparator<? super T> order;

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Create a sorter using a particular comparator.
   *
   * @param comparator The order in which elements in the array should be ordered after sorting.
   */
  public SelectionSorter(Comparator<? super T> comparator) {
    this.order = comparator;
  } // SelectionSorter(Comparator)

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Sort an array in place using selection sort.
   *
   * @param values an array to sort.
   * @post The array has been sorted according to some order (often one given to the constructor).
   * @post For all i, 0 &lt; i &lt; values.length, order.compare(values[i-1], values[i]) &lt;= 0
   */
  @Override
  public void sort(T[] values) {
    int index = 0;
    while (index < values.length - 1) {
      int minIndex = select(values, index);
      ArrayUtils.swap(values, minIndex, index);
      index++;
    } // while
  } // sort(T[])

  /**
   * Finds the minimum value within the array starting from beginning and return the index.
   *
   * @param values The list of values
   * @param beginning Index to starting searching from
   * @return Index of selected value
   */
  private int select(T[] values, int beginning) {
    int minIndex = beginning;
    for (int i = beginning + 1; i < values.length; ++i) {
      if (order.compare(values[minIndex], values[i]) > 0) {
        minIndex = i;
      } // if
    } // for
    return minIndex;
  } // select(T[], int)
} // class SelectionSorter
