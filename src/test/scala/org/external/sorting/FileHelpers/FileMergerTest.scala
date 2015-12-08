package org.external.sorting.FileHelpers

import java.io.File


object FileMergerTest extends FileLoader{

  def teardown(ls:Array[String]): Unit = {
    ls.foreach(file => new File(file).delete())
  }

  def main(args: Array[String]) {
    val lf: Array[String] = Array("test-files/FM-Files/file1",
                                  "test-files/FM-Files/file2",
                                  "test-files/FM-Files/file3")
    assert(mergeTest(lf))
  }

  private def mergeTest(lf:Array[String]): Boolean = {
    var ret_val = false
    val file: String = "test2" + util.Random.nextInt
    val fs:FileMerger = new FileMerger(lf, file)
    fs.merge()

    val arr: Array[String] = loadFile(file).toArray
    val clone: Array[String] = arr.clone()
    if (arr.deep == clone.sorted.deep) { ret_val = true }
    teardown(Array(file))
    ret_val
  }
}
