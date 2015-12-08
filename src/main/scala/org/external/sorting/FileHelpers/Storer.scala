package org.external.sorting.FileHelpers

import java.io.{FileWriter, IOException, File, PrintWriter}
import java.nio.file.{Files, Paths}


/**
 * Trait that stores into a file the contents
 * of an Array so it can be used anywhere
 */
trait Storer {
  var fileName: String = "tmp-file-buff-" + util.Random.nextInt

  private val newline = System.getProperty("line.separator")

  protected def saveToFile(itr:Array[_]):String = {
    try {
      val p: PrintWriter = new PrintWriter(new File(fileName))
      itr.foreach(e => p.println(e.toString))
      p.close()
    } catch {
      case e: IOException => println(s"File $fileName caused an IOException")
    }
    fileName
  }

  protected def appendToFile(itr:Array[_]):String = {
    try {
      if(Files.notExists(Paths.get(fileName))) {
        saveToFile(itr)
      }
      else {
        val fw: FileWriter = new FileWriter(fileName, true)
        itr.foreach(e => fw.write(e.toString + newline))
        fw.close()
      }
    } catch {
      case e: IOException => println(s"File $fileName caused an IOException")
    }
    fileName
  }
}