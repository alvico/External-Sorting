package org.external.sorting.FileHelpers

import scala.collection.mutable.ArrayBuffer


object FileMergerTest extends FileLoader{


  def main(args: Array[String]) {
    val lf: ArrayBuffer[String] = new ArrayBuffer[String]()
    lf.append("test-files/FM-Files/file1")
    lf.append("test-files/FM-Files/file2")
    lf.append("test-files/FM-Files/file3")

    assert(mergeTest(lf))
  }

  private def mergeTest(lf:ArrayBuffer[String]): Boolean = {
    var ret_val = false
    val file: String = "test2" + util.Random.nextInt
    val fs:FileMerger = new FileMerger(lf, file)
    fs.merge()
    //TO-DO check it was correctly sorted
    val arr: Array[String] = loadFile(file).toArray
    val clone: Array[String] = arr.clone()
    if (arr.deep == clone.sorted.deep) { ret_val = true }
    ret_val
  }
}
