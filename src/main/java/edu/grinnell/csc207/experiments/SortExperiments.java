package edu.grinnell.csc207.experiments;

import edu.grinnell.csc207.sorting.MergeSorter;
import edu.grinnell.csc207.sorting.Sorter;

import java.io.PrintWriter;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Some tools for quick experiments with sorting routines.
 *
 * @author Samuel A. Rebelsky.
 */
public class SortExperiments {
  // +---------+-----------------------------------------------------
  // | Helpers |
  // +---------+

  /**
   * Run an experiment on a single array.
   *
   * @param pen
   *   For printing out info.
   * @param values
   *   The array of values to sort.
   * @param sorter
   *   The sorter to use.
   */
  public static <T> void oneExperiment(PrintWriter pen, T[] values, 
      Sorter<T> sorter) {
    pen.printf("sort(%s) -> ", Arrays.toString(values));
    pen.flush();
    sorter.sort(values);
    pen.printf("%s\n\n", Arrays.toString(values));
  } // sortExperiment(PrintWriter, String[], Sorter<String>)

  /**
   * Run multiple experiments.
   *
   * @param pen
   *   For printing out info.
   * @param sorter
   *   The sorter to use.
   */
  public static void manyExperiments(PrintWriter pen, 
      Sorter<Comparable> sorter) {
    // A singleton array.
    oneExperiment(pen, new String[] {"a"}, sorter);
    // An array of integers that gave same trouble.
    oneExperiment(pen, 
        new Integer[] {28, 37, 20, 55, 10, 17, 9, 17}, 
        sorter);
  } // sortExperiments(PrintWriter, Sorter<String>)

  public static void main(String args[]) { 
    Integer[] in = {17, 47, 97, 94, 85, 84, 95, 18, 39, 63, 8, 90, 62, 33, 76, 89, 50, 71, 75, 74, 78, 1, 3, 37, 14, 45, 12, 69, 10, 77, 38, 26, 25, 36, 65, 20, 91, 60, 27, 2, 44, 13, 80, 0, 98, 73, 79, 67, 30, 23, 42, 66, 54, 88, 46, 61, 59, 57, 31, 21, 58, 4, 34, 5, 48, 55, 51, 86, 92, 43, 24, 83, 41, 82, 53, 32, 28, 9, 22, 99, 11, 96, 7, 70, 68, 35, 40, 6, 87, 29, 19, 16, 49, 81, 64, 52, 56, 15, 93, 72};
    oneExperiment(new PrintWriter(System.out, true), in, new MergeSorter<Integer>((x,y) -> x.compareTo(y)));
  }
 
} // class SortExperiments
