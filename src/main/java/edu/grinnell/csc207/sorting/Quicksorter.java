package edu.grinnell.csc207.sorting;

import java.util.Comparator;
import java.util.Random;

import edu.grinnell.csc207.util.ArrayUtils;

/**
 * Something that sorts using Quicksort.
 *
 * @param <T>
 *   The types of values that are sorted.
 *
 * @author Samuel A. Rebelsky
 */

public class Quicksorter<T> implements Sorter<T> {
  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The way in which elements are ordered.
   */
  Comparator<? super T> order;

  /**
   * Random number generator used to pick pivot.
   */
  Random rand = new Random();

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Create a sorter using a particular comparator.
   *
   * @param comparator
   *   The order in which elements in the array should be ordered
   *   after sorting.
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
   * @param values
   *   an array to sort.
   *
   * @post
   *   The array has been sorted according to some order (often
   *   one given to the constructor).
   * @post
   *   For all i, 0 &lt; i &lt; values.length,
   *     order.compare(values[i-1], values[i]) &lt;= 0
   */
  @Override
  public void sort(T[] values) {
    sort(values, 0, values.length);
  } // sort(T[])

  /**
   * Sort an array in place using Quicksort.
   * @param values Array of values
   * @param lowerBound Start of sorting section
   * @param higherBound End of sorting section
   */
  private void sort(T[]values, int lowerBound, int higherBound) { 
    if(lowerBound < higherBound - 1) { 
      int pivotIndex = partition(values, lowerBound, higherBound);

      sort(values, lowerBound, pivotIndex);
      sort(values, pivotIndex + 1, higherBound);
    } // if at least 2 values between low and high
  }

  /**
   * Partition the values between lower bound and higher bound.
   * Chooses a pivot. Values less than the pivot will be moved 
   * towards the left, values greater than the pivot will be 
   * moved towards the right. 
   * @param values The list of values
   * @param lowerBound Lower bound
   * @param higherBound Higher bound
   * @return Index of pivot / partition
   */
  private int partition(T[] values, int lowerBound, int higherBound) { 
    int pivot = rand.nextInt(lowerBound, higherBound);

    //Move pivot to the end
    ArrayUtils.swap(values, pivot, higherBound - 1);

    // values[lowerBound:leftPartIndex] < values[pivot]
    int leftPartIndex = lowerBound; 
    for(int i = lowerBound; i < higherBound - 1; ++i) { 
      if(order.compare(values[i], values[higherBound - 1]) < 0) { 
        ArrayUtils.swap(values, leftPartIndex, i);
        leftPartIndex++;
      } // if value less than pivot
    } // for values between lower and higher

    //Move pivot back
    ArrayUtils.swap(values, higherBound - 1, leftPartIndex);

    return leftPartIndex; 
  } // partition(T[], int, int)
} // class Quicksorter
