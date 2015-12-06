package org.external.sorting.FileHelpers

import main.SortHelpers.ArrayHandler

import scala.collection.mutable.ArrayBuffer
import scala.io.Source


/**
 * Class that implements the second part of the algorithm
 * Reads the files and sorts the different lines and merges them
 * into a new ordered file
 *
 * @param listToMerge list of filenames where the content is splitted
 * @param out name of the outpuf file
 */
class FileMerger(val listToMerge:ArrayBuffer[String], val out:String) {

  /**
   * Helper function that creates and iterator that reads from a file
   * @param file name of the file
   * @return iterator with the lines
   */
  private def loadFile(file:String): Iterator[String] = Source.fromFile(file).getLines()

  /**
   * Loads all the iterators into an array of iterators
   * generates aux arrays that contains the different lines to sort from the iteratos
   * orders them and meges them all together into the out put file
   */
  def merge():Unit = {
    val itrList: Array[Iterator[String]] = new Array[Iterator[String]](listToMerge.length)
    var index: Int = 0
    for ( file <- listToMerge) {
      itrList.update(index, loadFile(file))
      index+=1
    }

    while (itrList.forall(a=>a.nonEmpty)) {
      val aux: ArrayBuffer[String] = new ArrayBuffer[String]()
      for( a <- itrList) {
        if(a.nonEmpty) { aux.append(a.next()) }
      }
      val ah: ArrayHandler = new ArrayHandler(aux.toArray)
      ah.fileName = out
      ah.quickSort()
    }
  }
}
