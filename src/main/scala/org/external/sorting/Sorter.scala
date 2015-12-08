package org.external.sorting

import java.io.File

import org.external.sorting.FileHelpers.{FileMerger, FileSplitter}

object Sorter {

  def teardown(ls:Array[String]): Unit = {
    ls.foreach(file => new File(file).delete())
  }

  def main(args: Array[String]) {
    if(args.length!=1) { throw new Exception("no file name  provided")}
    val fileName:String = args(0)
    val fs:FileSplitter = new FileSplitter(fileName)
    fs.process()
    val fm: FileMerger = new FileMerger(fs.listOfFiles, fileName+"_sorted")
    fm.merge()
    teardown(fs.listOfFiles)
  }
}
