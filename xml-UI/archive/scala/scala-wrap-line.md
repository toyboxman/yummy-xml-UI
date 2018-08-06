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

object WrapUtil {

  def using[A <: { def close(): Unit }, B](param: A)(f: A => B): B = {
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

  def main(args: Array[String]): Unit = {
    val filename = "./pom.xml"

    val file = readTextFileWithTry(filename)
    file match {
      case Success(lines) => {
        val content = new ListBuffer[String]()
        lines.foreach(lne => {
          if (lne.length > 80) {
            val t = lne.splitAt(50)

            val nlne = t._1 + '\n' + t._2
            content.+=(nlne)
          } else {
            content.+=(lne)
          }
        })
        println(content.toList)
        save("./aa.xml", content.toList)
      }
      case Failure(s) => println(s"Failed, message is: $s")
    }
  }

}

```