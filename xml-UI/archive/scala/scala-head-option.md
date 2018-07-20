***

## Use headOption instead of head

### head potentially leads NPE
scala的集合提供获取第一个元素操作，但如果是空集合就会导致空指针异常抛出
```scala
import scala.util.Random

object HelloWorld {
   def main(args: Array[String]) {
      println("Hello, world!")
      
      val int: Int = Random.nextInt(10)
      println(int match {
        case 0 => "zero"
        case 1 => "one"
        case 2 => "two"
        case _ => "many"
      })
      
      val iter: Iterable[Int] = 1 to 4
      println(iter)
      val x = iter.take(2)
      println(x)
      
      val i01: Iterable[Int] = List(10,2,3,4,5)
      val iterator = i01.iterator
      if (iterator.hasNext) 
        println(iterator.next)
      
      val s: Iterable[String] = "one string" :: "two string" :: Nil
      println(s.head)
      //++ is for collection aggregation
      val ss = (s ++ Seq("three")).mkString(";\n")
      println(ss)
      
      val s1: Iterable[String] = Nil
      println(s1.headOption.getOrElse{None})
      val str = s1.headOption.getOrElse{None} match {
          case None => "empty"
          case _ => s1
      }
      println(str)
      
      val s2: Iterable[String] = Nil
      val hv = s2.headOption.orNull
      println(hv match {
          case null => "null value"
          case _ => "true value"
      })
      println(s2.head)
   }
}
``` 
运行代码
```scala 
$scalac *.scala
$scala HelloWorld

Hello, world!
many
Range(1, 2, 3, 4)
Range(1, 2)
10
one string
one string;
two string;
three
None
empty
null value
java.util.NoSuchElementException: head of empty list
	at scala.collection.immutable.Nil$.head(List.scala:337)
	at scala.collection.immutable.Nil$.head(List.scala:334)
	at HelloWorld$.main(HelloWorld.scala:24)
	at HelloWorld.main(HelloWorld.scala)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:498)
	at scala.tools.nsc.util.ScalaClassLoader$$anonfun$run$1.apply(ScalaClassLoader.scala:71)
	at scala.tools.nsc.util.ScalaClassLoader$class.asContext(ScalaClassLoader.scala:31)
	at scala.tools.nsc.util.ScalaClassLoader$URLClassLoader.asContext(ScalaClassLoader.scala:139)
	at scala.tools.nsc.util.ScalaClassLoader$class.run(ScalaClassLoader.scala:71)
	at scala.tools.nsc.util.ScalaClassLoader$URLClassLoader.run(ScalaClassLoader.scala:139)
	at scala.tools.nsc.CommonRunner$class.run(ObjectRunner.scala:28)
	at scala.tools.nsc.ObjectRunner$.run(ObjectRunner.scala:45)
	at scala.tools.nsc.CommonRunner$class.runAndCatch(ObjectRunner.scala:35)
	at scala.tools.nsc.ObjectRunner$.runAndCatch(ObjectRunner.scala:45)
	at scala.tools.nsc.MainGenericRunner.runTarget$1(MainGenericRunner.scala:74)
	at scala.tools.nsc.MainGenericRunner.process(MainGenericRunner.scala:96)
	at scala.tools.nsc.MainGenericRunner$.main(MainGenericRunner.scala:105)
	at scala.tools.nsc.MainGenericRunner.main(MainGenericRunner.scala)
```

对程序稍作修改
```scala
object HelloWorld {
   def main(args: Array[String]) {
      println("Hello, world!")
      val iter: Iterable[Int] = 1 to 4
      println(iter)
      val x = iter.take(2)
      println(x)
      
      val s: Iterable[String] = "one string" :: "two string" :: Nil
      println(s.head)
      
      val s1: Iterable[String] = Nil
      println(s1.headOption.getOrElse{None})
      val str = s1.headOption.getOrElse{None} match {
          case None => "empty"
          case _ => s1
      }
      println(str)
      
      val s2: Iterable[String] = Nil
      println(s2.head)
   }
}
```
运行代码
```scala
$scalac *.scala
$scala HelloWorld
Hello, world!
Range(1, 2, 3, 4)
Range(1, 2)
one string
None
empty
java.util.NoSuchElementException: head of empty list
	at scala.collection.immutable.Nil$.head(List.scala:337)
	at scala.collection.immutable.Nil$.head(List.scala:334)
	at HelloWorld$.main(HelloWorld.scala:12)
	at HelloWorld.main(HelloWorld.scala)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:498)
	at scala.tools.nsc.util.ScalaClassLoader$$anonfun$run$1.apply(ScalaClassLoader.scala:71)
	at scala.tools.nsc.util.ScalaClassLoader$class.asContext(ScalaClassLoader.scala:31)
	at scala.tools.nsc.util.ScalaClassLoader$URLClassLoader.asContext(ScalaClassLoader.scala:139)
	at scala.tools.nsc.util.ScalaClassLoader$class.run(ScalaClassLoader.scala:71)
	at scala.tools.nsc.util.ScalaClassLoader$URLClassLoader.run(ScalaClassLoader.scala:139)
	at scala.tools.nsc.CommonRunner$class.run(ObjectRunner.scala:28)
	at scala.tools.nsc.ObjectRunner$.run(ObjectRunner.scala:45)
	at scala.tools.nsc.CommonRunner$class.runAndCatch(ObjectRunner.scala:35)
	at scala.tools.nsc.ObjectRunner$.runAndCatch(ObjectRunner.scala:45)
	at scala.tools.nsc.MainGenericRunner.runTarget$1(MainGenericRunner.scala:74)
	at scala.tools.nsc.MainGenericRunner.process(MainGenericRunner.scala:96)
	at scala.tools.nsc.MainGenericRunner$.main(MainGenericRunner.scala:105)
	at scala.tools.nsc.MainGenericRunner.main(MainGenericRunner.scala)
```