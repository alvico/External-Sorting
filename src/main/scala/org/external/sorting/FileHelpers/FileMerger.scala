package org.external.sorting.FileHelpers

import org.external.sorting.SortHelpers.ArrayHandler

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
class FileMerger(val listToMerge:Array[String], val out:String) {

  /**
   * Helper function that creates and iterator that reads from a file
   * @param file name of the file
   * @return iterator with the lines
   */
  private def loadFile(file:String): Iterator[String] = Source.fromFile(file).getLines()


  /**
   * Size of the outputbuffer
   */
   var outputBuffer:Int = 4

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

    val aux: ArrayBuffer[String] = new ArrayBuffer[String]()

    while (itrList.exists(a=>a.nonEmpty)) {
      for( a <- itrList ) {
        if(a.nonEmpty) {
          aux.append(a.next())
          if ( aux.length == outputBuffer) {
            saveAuxArray(aux)
          }
        }
      }
    }
    if (aux.nonEmpty) {
      saveAuxArray(aux)
    }
  }

  /**
   * Helper function that saves and sorts and array
   * @param aux array to sort and save
   */
  private def saveAuxArray(aux:ArrayBuffer[String]): Unit = {
    val ah: ArrayHandler = new ArrayHandler(aux.toArray, Array(out))
    ah.quickSort()
    aux.clear()
  }
}
