package org.external.sorting.SortHelpers

import java.io.File

import scala.io.Source



object ArrayHandlerTest {

  private def loadFile(file:String): Iterator[String] = Source.fromFile(file).getLines()

  val unord: Array[String] =
    Array("my", "daughter", "is", "sick", "and", "crying")
  val ord: Array[String] =
    Array("and", "crying", "daughter", "is", "my", "sick")
  def main (args: Array[String]) {
    val ah: ArrayHandler = new ArrayHandler(unord)
    val quick: (Boolean, Array[String]) = testQuickSort(ah)
    if (!quick._1)
      throw new Exception(s"QuickSort Failed: ${quick._2.mkString(" ")}")
    val ah2: ArrayHandler = new ArrayHandler(unord)
    val sort: (Boolean, Array[String]) = testSort(ah2)
    if (!sort._1)
      throw new Exception(s"Sort Failed: ${sort._2.mkString(" ")}")
  }

  private def testQuickSort(ah: ArrayHandler): (Boolean, Array[String]) = {
    ah.quickSort()
    val itr:Iterator[String] = loadFile(ah.fileName)
    var ret_val:Boolean = false
    val arr: Array[String] =  itr.toArray
    if (arr.deep == ord.deep) {ret_val = true}
    new File(ah.fileName).delete()
    (ret_val, itr.toArray)
  }

  private def testSort(ah: ArrayHandler): (Boolean, Array[String]) = {
    ah.sort()
    val itr:Iterator[String] = loadFile(ah.fileName)
    var ret_val:Boolean = false
    val arr: Array[String] =  itr.toArray
    if (arr.deep == ord.deep) {ret_val = true}
    new File(ah.fileName).delete()
    (ret_val, itr.toArray)
  }
}
