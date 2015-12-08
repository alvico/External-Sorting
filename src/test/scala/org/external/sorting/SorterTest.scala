package org.external.sorting

import java.io.File

import org.external.sorting.FileHelpers.FileLoader


object SorterTest extends FileLoader{

  def teardown(ls:Array[String]): Unit = {
    ls.foreach(file => new File(file).delete())
  }

  def main(args: Array[String]) {
    val sort = Sorter
    sort.main(Array("test-files/test2"))
    val itr1: Array[String] = loadFile("test-files/test2").toArray
    val itr2: Array[String] = loadFile("test-files/test2_sorted").toArray

    assert(itr1.deep == itr2.deep)

    teardown(Array("test-files/test2_sorted"))
  }
}
