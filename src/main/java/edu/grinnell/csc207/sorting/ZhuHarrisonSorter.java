package edu.grinnell.csc207.sorting;

import java.util.Comparator;

/**
 * Sorter by Harrison Zhu.
 * @param <T> Type to sort.
 */
public class ZhuHarrisonSorter<T> implements Sorter<T> {
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
  public ZhuHarrisonSorter(Comparator<? super T> comparator) {
    this.order = comparator;
  } // InsertionSorter(Comparator)

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Merge sort, but when length is less than 4, switch to insertion sort.
   */
  @Override
  @SuppressWarnings("unchecked")
  public void sort(T[] values) {
    T[] helper = (T[]) new Object[values.length];
    mergeSort(values, 0, values.length, helper);

    // ArrayList<Integer> bounds = new ArrayList<>();
    // bounds.add(0);
    // boolean initialIncreasing = order.compare(values[1], values[0]) >= 0; // equal numbers are
    // considered increasing
    // boolean increasing = initialIncreasing;
    // for(int i = 1; i < values.length; ++i) {
    //     if(order.compare(values[i], values[i-1]) > 0 && !increasing) { // increasing and was
    // decreasing
    //         ArrayUtils.reverse(values, bounds.get(bounds.size() - 1), i);
    //         increasing = true;
    //         bounds.add(i);
    //     } else if(order.compare(values[i], values[i-1]) < 0 && increasing) { // decreasing and
    // was increasing
    //         increasing = false;
    //         bounds.add(i);
    //     } else { // equal

    //     }
    // }
    // System.out.println("AHHHHHHH");
    // for(int i = 0; i < values.length; ++i) {
    //     System.out.print(values[i] + " ");
    //     if(bounds.contains(i)) {
    //         System.out.print("<| ");
    //     }
    // }
    // System.out.println("AHHHHHHH");
  } // sort(T[])

  /**
   * Sort an array in place using merge sort.
   *
   * @param values Array of values
   * @param low Lower bound of section to be sorted
   * @param high Higher bound of section to be sorted
   * @param helper Helper array
   */
  private void mergeSort(T[] values, int low, int high, T[] helper) {
    int mid = (high - low) / 2 + low;
    if (high - low > 4) {
      mergeSort(values, low, mid, helper); // sort left
      mergeSort(values, mid, high, helper); // sort right
      merge(values, low, mid, high, helper); // merge in place
    } else { // if section length > 4
      insertionSort(values, low, high);
    } // if else
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

  /**
   * Sort an array in place using insertion sort.
   *
   * @param values an array to sort.
   * @param lower lower bound
   * @param higher higher bound
   * @post The array has been sorted according to some order (often one given to the constructor).
   * @post For all i, 0 &lt; i &lt; values.length, order.compare(values[i-1], values[i]) &lt;= 0
   */
  public void insertionSort(T[] values, int lower, int higher) {
    for (int i = lower + 1; i < higher; ++i) {
      insert(values, i, lower, higher);
    } // for
  } // sort(T[])

  /**
   * Insert the value at index beginning into a sorted position within the array slice
   * values[0:beginning].
   *
   * @param values Array of values to sort
   * @param beginning Beginning of unsorted section
   * @param lower lower bound
   * @param higher higher bound
   */
  private void insert(T[] values, int beginning, int lower, int higher) {
    T val = values[beginning];
    int j = beginning - 1;
    while (j >= lower && order.compare(values[j], val) > 0) {
      values[j + 1] = values[j];
      j--;
    } // while
    values[j + 1] = val;
  } // insert(T[], int)
} // class InsertionSorter
