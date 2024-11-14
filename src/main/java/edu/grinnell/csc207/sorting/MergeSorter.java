package edu.grinnell.csc207.sorting;

import java.util.Comparator;

/**
 * Something that sorts using merge sort.
 *
 * @param <T> The types of values that are sorted.
 * @author Samuel A. Rebelsky
 */
public class MergeSorter<T> implements Sorter<T> {
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
  public MergeSorter(Comparator<? super T> comparator) {
    this.order = comparator;
  } // MergeSorter(Comparator)

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Sort an array in place using merge sort.
   *
   * @param values an array to sort.
   * @post The array has been sorted according to some order (often one given to the constructor).
   * @post For all i, 0 &lt; i &lt; values.length, order.compare(values[i-1], values[i]) &lt;= 0
   */
  @Override
  @SuppressWarnings("unchecked")
  public void sort(T[] values) {
    T[] helper = (T[]) new Object[values.length];
    sort(values, 0, values.length, helper);
  } // sort(T[])

  /**
   * Sort an array in place using merge sort.
   *
   * @param values Array of values
   * @param low Lower bound of section to be sorted
   * @param high Higher bound of section to be sorted
   * @param helper Helper array
   */
  private void sort(T[] values, int low, int high, T[] helper) {
    int mid = (high - low) / 2 + low;
    if (high - low > 1) {
      sort(values, low, mid, helper); // sort left
      sort(values, mid, high, helper); // sort right
      merge(values, low, mid, high, helper); // merge in place
    } // if section length > 1
  } // sort(T[], int, int, T[])

  /**
   * Merging two sorted section of array into one sorted section. The helper array is used as a
   * temporary storage of values. values[low:mid] and value[mid:high] will be merged into a single
   * sorted section values[low:high]
   *
   * @param values Array of values
   * @param low Lower bound of section to be merged
   * @param mid Middle of the section to be merged
   * @param high Higher bound of section to be merged
   * @param helper Helper array
   */
  private void merge(T[] values, int low, int mid, int high, T[] helper) {
    // left = values[low:mid]
    // right = values[mid:high]

    int leftLength = mid - low;
    int rightLength = high - mid;

    int leftStart = low;
    int rightStart = mid;

    // Double interator, while neither sides used all their values
    int i = 0;
    int j = 0;
    while (i < leftLength && j < rightLength) {
      if (order.compare(values[leftStart + i], values[rightStart + j])
          < 0) { // if left is less than right
        helper[low + i + j] = values[leftStart + i];
        i++;
      } else {
        helper[low + i + j] = values[rightStart + j];
        j++;
      } // if right is less than left
    } // while

    // Inserting leftover values from the left side
    for (int k = i; k < leftLength; ++k) {
      helper[low + k + j] = values[leftStart + k];
    } // for

    // Inserting leftover values from the right side
    for (int k = j; k < rightLength; ++k) {
      helper[low + k + i] = values[rightStart + k];
    } // for

    // Copying values from helper to values array
    for (int k = low; k < high; ++k) {
      values[k] = helper[k];
    } // for
  } // merge
} // class MergeSorter
