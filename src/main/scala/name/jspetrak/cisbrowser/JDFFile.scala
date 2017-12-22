package name.jspetrak.cisbrowser

class JDFFile(val fileName: String, val carriers: String, val lines: String) {

  override def toString: String = s"file:$fileName,carriers:\n$carriers\nlines:\n;$lines\n\n"

}
