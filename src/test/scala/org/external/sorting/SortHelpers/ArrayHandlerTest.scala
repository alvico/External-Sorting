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

    val quick: (Boolean, Array[String]) = testQuickSort()
    assert(quick._1, s"QuickSort Failed: ${quick._2.mkString(" ")}")
    val sort: (Boolean, Array[String]) = testSort()
    assert(sort._1,s"Sort Failed: ${sort._2.mkString(" ")}" )

  }

  private def testQuickSort(): (Boolean, Array[String]) = {
    val file:String = "test-file" + util.Random.nextInt
    val ah: ArrayHandler = new ArrayHandler(unord, Array(file))
    ah.quickSort()
    val itr:Iterator[String] = loadFile(file)
    var ret_val:Boolean = false
    val arr: Array[String] =  itr.toArray
    if (arr.deep == ord.deep) {ret_val = true}
    new File(file).delete()
    (ret_val, itr.toArray)
  }

  private def testSort(): (Boolean, Array[String]) = {
    val file:String = "test-file" + util.Random.nextInt
    val ah: ArrayHandler = new ArrayHandler(unord, Array(file))
    ah.sort()
    val itr:Iterator[String] = loadFile(file)
    var ret_val:Boolean = false
    val arr: Array[String] =  itr.toArray
    if (arr.deep == ord.deep) {ret_val = true}
    new File(file).delete()
    (ret_val, itr.toArray)
  }
}
