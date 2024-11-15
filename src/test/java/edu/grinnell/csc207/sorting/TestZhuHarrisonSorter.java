package edu.grinnell.csc207.sorting;

import org.junit.jupiter.api.BeforeAll;

/** Tests of our InsertionSorter. */
public class TestZhuHarrisonSorter extends TestSorter {
  /** Set up the sorters. */
  @BeforeAll
  static void setup() {
    stringSorter = new ZhuHarrisonSorter<String>((x, y) -> x.compareTo(y));
    intSorter = new ZhuHarrisonSorter<Integer>((x, y) -> x.compareTo(y));
  } // setup()
} // class TestInsertionSorter
