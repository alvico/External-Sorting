package FileHelpers

import java.io.{IOException, File, PrintWriter}


/**
 * Trait that stores into a file the contents
 * of an Array so it can be used anywhere
 */
trait Storer {
  var fileName: String = "tmp-file-buff-" + util.Random.nextInt

  protected def saveToFile(itr:Array[_]):String = {
    try {
      val p: PrintWriter = new PrintWriter(new File(fileName))
      itr.foreach(e => p.write(e.toString))
      p.close()
    } catch {
      case e: IOException => println(s"File $fileName caused an IOException")
    }
    fileName
  }
}