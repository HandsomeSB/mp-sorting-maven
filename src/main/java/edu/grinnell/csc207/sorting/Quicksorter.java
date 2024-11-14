package edu.grinnell.csc207.sorting;

import edu.grinnell.csc207.util.ArrayUtils;
import java.util.Comparator;
import java.util.Random;

/**
 * Something that sorts using Quicksort.
 *
 * @param <T> The types of values that are sorted.
 * @author Samuel A. Rebelsky
 */
public class Quicksorter<T> implements Sorter<T> {
  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /** The way in which elements are ordered. */
  Comparator<? super T> order;

  /** Random number generator used to pick pivot. */
  Random rand = new Random();

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Create a sorter using a particular comparator.
   *
   * @param comparator The order in which elements in the array should be ordered after sorting.
   */
  public Quicksorter(Comparator<? super T> comparator) {
    this.order = comparator;
  } // Quicksorter(Comparator)

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Sort an array in place using Quicksort.
   *
   * @param values an array to sort.
   * @post The array has been sorted according to some order (often one given to the constructor).
   * @post For all i, 0 &lt; i &lt; values.length, order.compare(values[i-1], values[i]) &lt;= 0
   */
  @Override
  public void sort(T[] values) {
    sort(values, 0, values.length);
  } // sort(T[])

  /**
   * Sort an array in place using Quicksort.
   *
   * @param values Array of values
   * @param lowerBound Start of sorting section
   * @param higherBound End of sorting section
   */
  private void sort(T[] values, int lowerBound, int higherBound) {
    if (lowerBound < higherBound - 1) {
      int[] dnfBounds = dnf(values, lowerBound, higherBound);

      sort(values, lowerBound, dnfBounds[0]);
      sort(values, dnfBounds[1], higherBound);
    } // if at least 2 values between low and high
  } // if

  /**
   * Apply the Dutch national flag algorithm within values between lowerBound and higherBound.
   * Organizes data into
   *
   * <p>less than | equal to | greater than
   *
   * @param values Array of values to organize
   * @param lowerBound Lower bound
   * @param higherBound Higher bound
   * @return Array of indices that separates the three sections
   */
  private int[] dnf(T[] values, int lowerBound, int higherBound) {
    // less than  | equal to  | unprocessed | greater than
    //            r           w             b

    int r = lowerBound;
    int w = lowerBound;
    int b = higherBound;

    int pivot = rand.nextInt(lowerBound, higherBound);
    T pivotVal = values[pivot];

    while (b > w) {
      T unprocessedValue = values[w];
      if (order.compare(unprocessedValue, pivotVal) > 0) {
        ArrayUtils.swap(values, w, --b);
      } else if (order.compare(unprocessedValue, pivotVal) == 0) {
        w++;
      } else {
        ArrayUtils.swap(values, w++, r++);
      } // if else
    } // while

    return new int[] {r, w};
  } // while
} // class Quicksorter
