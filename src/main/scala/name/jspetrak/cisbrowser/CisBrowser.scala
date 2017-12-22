package name.jspetrak.cisbrowser

import java.util.Enumeration
import java.util.zip.{ZipEntry, ZipFile}

import scala.collection.mutable.ArrayBuffer

object CisBrowser extends App {

  val temp = new ArrayBuffer[JDFFile]()

  val cisFolderPath: String = "/Users/bileto/Workspace/CISJR/20171219"
  val jdfFilePath: String = s"$cisFolderPath/JDF/JDF.zip"

  val jdfArchivesFile: ZipFile = new ZipFile(jdfFilePath)
  val jdfFiles: Enumeration[_ <: ZipEntry] = jdfArchivesFile.entries()

  while (jdfFiles.hasMoreElements) {
    val jdfArchiveEntry: ZipEntry = jdfFiles.nextElement()
    val jdfEntryReader: JDFReader = JDFReader.fromEntry(jdfArchivesFile, jdfArchiveEntry)

    val carriers = jdfEntryReader.readCarriers()
    val lines = jdfEntryReader.readLines()

    temp += new JDFFile(jdfArchiveEntry.getName(), carriers, lines)
  }

  temp.foreach(println(_))

}
