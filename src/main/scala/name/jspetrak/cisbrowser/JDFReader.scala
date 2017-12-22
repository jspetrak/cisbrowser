package name.jspetrak.cisbrowser

import java.io.File
import java.nio.file.{Files, StandardCopyOption}
import java.util.Scanner
import java.util.zip.{ZipEntry, ZipFile}

class JDFReader {

  private var zipFile: ZipFile = null

  private def this(zipFile: ZipFile) {
    this()

    this.zipFile = zipFile
  }

  def readCarriers(): String = {
    return readFile("Dopravci.txt")
  }

  def readLines(): String = {
    return readFile("Linky.txt")
  }

  private def readFile(name: String): String = {
    val entryLines: ZipEntry = zipFile.getEntry(name)
    val scannerLines: Scanner = new Scanner(zipFile.getInputStream(entryLines), "cp1250").useDelimiter("\\A")
    val content: String = if (scannerLines.hasNext()) scannerLines.next() else ""

    content
  }

}

object JDFReader {

  def fromEntry(file: ZipFile, entry: ZipEntry): JDFReader = {
    val tmpFile = File.createTempFile("cisbrowser", ".tmp")
    tmpFile.deleteOnExit()

    Files.copy(file.getInputStream(entry), tmpFile.toPath, StandardCopyOption.REPLACE_EXISTING)

    return new JDFReader(new ZipFile(tmpFile))
  }

}
