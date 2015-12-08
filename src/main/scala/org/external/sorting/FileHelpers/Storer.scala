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
    itr.foreach(e =>saveLine(fileName, e.toString))
    fileName
  }

  protected def appendToFile(itr:Array[_]):String = {
    if(Files.notExists(Paths.get(fileName))) { saveToFile(itr) }
    else { itr.foreach(e => appendLine(fileName, e.toString)) }
    fileName
  }
  
  private def saveLine(file:String, line:String) : Unit = {
      val p: PrintWriter = new PrintWriter(new File(file))
      p.write(line)
      p.close()
  }

  protected def appendLine(file:String, line:String) : Unit = {
    try{
      val fw: FileWriter = new FileWriter(file, true)
      fw.write(line + newline)
      fw.close()
    } catch {
      case e: IOException => println(s"File $fileName caused an IOException")
    }
  }

}