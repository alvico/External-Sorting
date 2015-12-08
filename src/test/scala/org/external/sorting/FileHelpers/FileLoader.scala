package org.external.sorting.FileHelpers

import scala.io.Source


/**
 * Helper trait for loading files
 */
trait FileLoader {
  protected def loadFile(file:String): Iterator[String] =
    Source.fromFile(file).getLines()
}
