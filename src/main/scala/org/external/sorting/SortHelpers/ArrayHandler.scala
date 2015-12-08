package org.external.sorting.SortHelpers

import org.external.sorting.FileHelpers.Storer

import  scala.util.Sorting


/**
 * Class that sorts an array of strings
 * and stores it into a given file
 *
 * @param itr the array to sort
 */
class ArrayHandler(itr: Array[String], files:Array[String]) extends Storer {
  /**
   * Method that quicksorts the given array and saves it into a file
   *
   * @return file name where the info is stored
   */
  def quickSort(): Unit = {
    // Scala provides us with a nice sorted function
    // conversely we could use as well QuickSort method
    // from utils.Sorting.quicksort but according to some
    // documentation there is no major difference in performance
    Sorting.quickSort(itr)
    save(itr)
  }

  /**
   * Method that sorts the given array and saves intoa fiel
   *
   * @return file name where the info is stored
   */
  def sort(): Unit = {
    // Scala provides us with a nice sorted function
    itr.sorted
    save(itr)
  }

  private def save(itr:Array[String]) : Unit = {
    var a: Int = 0
    while (a < itr.length) {
      for (file <- files if a < itr.length) {
        appendLine(file, itr(a))
        a += 1
      }
    }
  }
}
