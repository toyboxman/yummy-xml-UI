***

## Use headOption instead of head

### head potentially leads NPE
scala的集合提供获取第一个元素操作，但如果是空集合就会导致空指针异常抛出
```scala
package org.apache.griffin.measure.utils

import scala.io.Source
import scala.util.{Try, Success, Failure}
import scala.collection.mutable.ListBuffer

import java.io._
import java.nio.file.{Paths, Files}

object WrapUtil {

  def using[A <: {def close() : Unit}, B](param: A)(f: A => B): B = {
    try {
      f(param)
    } finally {
      param.close()
    }
  }

  def readTextFileWithTry(filename: String): Try[List[String]] = {
    Try {
      val lines = using(Source.fromFile(filename)) { source =>
        (for (line <- source.getLines) yield line).toList
      }
      lines
    }
  }

  def wrapFile(filename: String): List[String] = {
    val file = readTextFileWithTry(filename)

    def splitLine(content: ListBuffer[String], lne: String, index: Int): ListBuffer[String] = {
      val ch = lne.trim.charAt(0)
      // locate a blank char and split on it, otherwise probably split a whole word
      val sp_pos = lne.lastIndexOf(32, index)
      val dot_pos = lne.lastIndexOf(46, index)
      val comma_pos = lne.lastIndexOf(44, index)
      val left_partentheis_pos = lne.lastIndexOf('(', index)
      // scala doesn't support ternary operator like java
      var pos = if (sp_pos > dot_pos) sp_pos else dot_pos
      pos = if (comma_pos > pos) comma_pos else pos
      pos = if (left_partentheis_pos > pos) left_partentheis_pos else pos
//      println(lne)
//      println(List[Int](sp_pos, dot_pos, comma_pos, left_partentheis_pos))
      if (pos < 2) {
        content.+=(lne)
        return content
      }
      val t = lne.splitAt(pos)
      var s = ""
      if (ch == '/') {
        content.+=(t._1)
        s = "//" + t._2
      } else if (ch == '*') {
        content.+=(t._1)
        s = "*" + t._2
      } else {
        content.+=(t._1)
        s = t._2
      }
      if (s.length > 40) {
        splitLine(content, s, 50)
      } else if (!s.isEmpty) {
        content.+=(s)
      }
      content
    }

    file match {
      case Success(lines) => {
        val content = new ListBuffer[String]()
        lines.foreach(lne => {
          // line's length is over 80 chars, try to wrap it
          if (lne.length > 80) {
            splitLine(content, lne, 80)
          } else {
            content.+=(lne)
          }
        })
        content.toList
      }
      case Failure(s) => {
        println(s"Failed, message is: $s")
        List("")
      }
    }
  }

  def save(filename: String, content: List[String]) = {
    // PrintWriter
    //    val pw = new PrintWriter(new File("hello.txt" ))
    //    pw.write("Hello, world")
    //    pw.close

    // FileWriter
    val file = new File(filename)
    val bw = new BufferedWriter(new FileWriter(file, false))
    content.foreach(text => bw.write(s"$text\n"))
    bw.close()
  }

  def getListOfFiles(fileName: String, fs: ListBuffer[String]): List[String] = {
    val path = Paths.get(fileName)
    if (Files.exists(path)) {
      if (Files.isDirectory(path)) {
        val d = new File(fileName)
        d.listFiles.filter(_.isDirectory).toList.foreach(dir => getListOfFiles(dir.getPath, fs))
        d.listFiles.filter(_.isFile).filter(_.getName.endsWith("java")).toList.foreach(f => fs.+=(f.getPath))
        fs.toList
      } else {
        fs.+=(fileName)
      }
    }
    fs.toList
  }

  def main(args: Array[String]): Unit = {
//    val filename = "./service/src/main/java/org/apache/griffin/core/config/EnvConfig.java"
    val filename = "./service"
    val flist = ListBuffer[String]()
    val files = getListOfFiles(filename, flist)
    println(s"file list size is:${flist.size}")
    files.foreach(
      name => {
        println(s"processing file[$name]")
        val content = wrapFile(name)
        save(name, content)
      }
    )
  }

}

```