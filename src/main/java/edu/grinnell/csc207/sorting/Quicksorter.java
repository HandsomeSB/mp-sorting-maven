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

  private void sort(T[]values, int lowerBound, int higherBound) { 
    if(lowerBound < higherBound - 1) { 
      int pivotIndex = sortPartition(values, lowerBound, higherBound);

      sort(values, lowerBound, pivotIndex);
      sort(values, pivotIndex + 1, higherBound);
    }
  }

  private int sortPartition(T[] values, int lowerBound, int higherBound) { 
    Random rand = new Random();
    int pivot = rand.nextInt(lowerBound, higherBound);

    ArrayUtils.swap(values, pivot, higherBound - 1);

    // values[lowerBound:leftPartIndex] < values[pivot]
    int leftPartIndex = lowerBound;
    for(int i = lowerBound; i < higherBound - 1; ++i) { 
      if(order.compare(values[i], values[higherBound - 1]) < 0) { 
        ArrayUtils.swap(values, leftPartIndex, i);
        leftPartIndex++;
      }
    }

    ArrayUtils.swap(values, higherBound - 1, leftPartIndex);

    return leftPartIndex; 
  }
} // class Quicksorter
