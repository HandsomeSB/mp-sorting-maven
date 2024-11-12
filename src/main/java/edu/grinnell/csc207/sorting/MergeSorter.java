package edu.grinnell.csc207.sorting;

import java.util.Comparator;

/**
 * Something that sorts using merge sort.
 *
 * @param <T>
 *   The types of values that are sorted.
 *
 * @author Samuel A. Rebelsky
 */

public class MergeSorter<T> implements Sorter<T> {
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
  public MergeSorter(Comparator<? super T> comparator) {
    this.order = comparator;
  } // MergeSorter(Comparator)

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Sort an array in place using merge sort.
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
    T[] newVals = mergeSort(values, 0, values.length);
    for(int i = 0; i < values.length; ++i) { 
      values[i] = newVals[i];
    }
    
  } // sort(T[])

  @SuppressWarnings("unchecked")
  private T[] mergeSort(T[] values, int low, int high) { 
    T[] ret = (T[]) new Object[high - low];

    if(high - low == 1) { 
      ret[0] = values[low];
    }

    if(high - low > 1) { 
      int mid = (high - low) / 2 + low;
      T[] first = mergeSort(values, low, mid);
      T[] second = mergeSort(values, mid, high);
      int i = 0, j = 0;
      while(i < first.length && j < second.length) { 
        if(order.compare(first[i], second[j]) < 0) { 
          ret[i + j] = first[i];
          i++;
        } else { 
          ret[i + j] = second[j];
          j++;
        }
      }

      for(int k = i; k < first.length; ++k) { 
        ret[k + j] = first[k];
      }

      for(int k = j; k < second.length; ++k) { 
        ret[k + i] = second[k];
      }
    }

    return ret;
  }
} // class MergeSorter
