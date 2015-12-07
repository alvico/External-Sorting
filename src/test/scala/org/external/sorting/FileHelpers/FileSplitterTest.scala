package org.external.sorting.FileHelpers

import java.io.File

import scala.collection.mutable.ArrayBuffer
import scala.io.Source


object FileSplitterTest {

  private def loadFile(file:String): Iterator[String] = Source.fromFile(file).getLines()

  def teardowntest(ls:ArrayBuffer[String]): Unit = {
    ls.foreach(file => new File(file).delete())
  }

  def main(args: Array[String]): Unit = {
    val file:String = "test-files/test1"
    val fs: FileSplitter = new FileSplitter(file)
    fs.maxRowsToSort = 4

    assert (processTestSmoke(fs))
    assert (processTestSortedFiles(fs.listOfFiles))

    teardowntest(fs.listOfFiles)
  }

  private def processTestSmoke(fs: FileSplitter): Boolean = {
    fs.process()
    var resp_val = false
    if (fs.listOfFiles.length == 3) { resp_val = true }
    else { throw new Exception("It should have generated 3 subfiles but") }
    resp_val
  }

  // method for testing that each of the content in the files
  // is Sorted properly
  private def processTestSortedFiles(ls:ArrayBuffer[String]): Boolean = {
    var resp_val = false
    for(file <-  ls) {
      val itr:Iterator[String] = loadFile(file)
      val aux: Array[String] = itr.toArray
      val aux2: Array[String] = aux.clone()
      if (aux.deep == aux2.sorted.deep) { resp_val = true}
    }
    resp_val
  }
}
