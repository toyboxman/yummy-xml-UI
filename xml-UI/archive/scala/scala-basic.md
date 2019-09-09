- [Basic Language Concepts](#basic-language-concepts)
    - [Keywords/Symbols](https://docs.scala-lang.org/tutorials/FAQ/finding-symbols.html)
        - [Scala语法结构速查表](https://docs.scala-lang.org/zh-cn/cheatsheets/index.html)
    - [Object/Case Class/Trait](#11)
        - [Companion object](#111)
        - [classOf/isInstanceOf/asInstanceOf](#112)
    - [Extends/With](#21)
        - [Upper Type Bounds <:](https://docs.scala-lang.org/tour/upper-type-bounds.html)
        - [Lower Type Bounds >:](https://docs.scala-lang.org/tour/lower-type-bounds.html)
        - [Advanced Type Bounds](https://twitter.github.io/scala_school/advanced-types.html#otherbounds)
    - [Implicit](#22)
    - [call by name/传名参数](https://docs.scala-lang.org/zh-cn/tour/by-name-parameters.html)
    - [Null/Nil/Nothing/Unit](#nullnilnothingunit)
    - [Option/Some/None](#optionsomenone)
    - [Def/Val](#defval)
        - [val/var](https://data-flair.training/blogs/scala-variables/)
    - [String](#string)
    - [Head](scala-head.md)
    - [Collection](scala-collections.md)
        - [Seq +:/++:/:+/:++](https://docs.scala-lang.org/zh-cn/overviews/collections/seqs.html)
- [Scala Compile](#scala-compile)
- [Samples](#scala-samples)
***

## Basic Language Concepts
基本语法规则参看
> [scala-syntax](https://data-flair.training/blogs/scala-syntax/)

> [tour-of-scala](https://docs.scala-lang.org/tour/tour-of-scala.html)

<div id="21">

### extends/with
Scala语法仅仅支持继承一个class, 如果有多个class或trait需要继承, 其中第一个继承关系使用extends class表示, 后面的trait就用with. 
```
trait T1
trait T2
class P1
class P2

class C1 extends T1
class C2 extends T1 with T2
class C3 extends T2 with T1
class C4 extends P1 with T1
/// class C5 extends T1 with P1 // invalid
/// class C6 extends P1 with P2 // invalid
```

<div id="22">

### Implicit
下面两个是典型的应用，参看[详细说明](https://www.artima.com/pins1ed/implicit-conversions-and-parameters.html)

- **Implicit parameters**

一个方法的最后的参数列表(final parameter list)可以标识成implicit, 这意味参数值将从调用context获得。如果没有正确类型的implicit值，将无法编译。为避免冲突implicit值必须解析(resolve)成一个唯一值，因此一个好实现方式是写代码时让类型与目的强一致相关，例如不要要求方法去发现implicit Int,  参看[example](https://alvinalexander.com/scala/scala-implicit-method-arguments-fields-example)
```
// probably in a library
class Prefixer(val prefix: String)
def addPrefix(s: String)(implicit p: Prefixer) = p.prefix + s

// then probably in your application
implicit val myImplicitPrefixer = new Prefixer("***")
addPrefix("abc")  // returns "***abc"
```

- **Implicit conversions**

当编译器从上下文中发现一个错误类型的表达式, 将寻找一个类型的implicit Function value，来进行typecheck。因此当需要A是却发现是B类型时候, 将寻找type B => A的implicit Function value。

例如当需要Int却发现Double类型，标注implicit的方法将被编译器插入一些实现。
```
implicit def doubleToInt(d: Double) = d.toInt
val x: Int = 42.0
```
实际会如下执行
```
def doubleToInt(d: Double) = d.toInt
val x: Int = doubleToInt(42.0)
```
第二段代码中我们手动进行类型转换，第一段代码则由编译器来自动完成。

### Null/Nil/Nothing/Unit
- **Null**: It's a Trait.
- **null**: Its an instance of Null- Similar to Java null.
- **Nothing**: is a Trait. It's a subtype of everything. But not superclass of anything. There are no instances of Nothing.
- **Nil**: Represents an empty List of anything of zero length. It's not that it refers to nothing but it refers to List which has no contents.
- **None**: Used to represent a sensible return value. Just to avoid null pointer exception. Option has exactly 2 subclasses- Some and None. None signifies no result from the method.
- **Unit**: Type of method that doesn't return a value of any sort.
```scala
# Nothing
// List of Strings
val fruit: List[String] = List("apples", "oranges", "pears")
// List of Integers
val nums: List[Int] = List(1, 2, 3, 4)
// Empty List.
val empty: List[Nothing] = List()

#Nil
scala> println (Nil == List())
true
scala> println (Nil eq List())
true
scala> println (Nil equals List())
true
scala> System.identityHashCode(Nil)
374527572
scala> System.identityHashCode(List())
374527572

scala> List(1, 2, 3).foldLeft(List[Int]())((x, y) => y :: x)
res6: List[Int] = List(3, 2, 1)
scala> List(1, 2, 3).foldLeft(Nil)((x, y) => y :: x)
<console>:10: error: type mismatch;
 found   : List[Int]
 required: scala.collection.immutable.Nil.type
       List(1, 2, 3).foldLeft(Nil)((x, y) => y :: x)
``` 

### String
```scala
val name = "James"
println(s"Hello, $name")  // Hello, James
==
StringContext("Hello, ", "").s(name)
```

### Option/Some/None
A powerful Scala idiom is to use the Option class when returning a value from a function that can be null.   
Because Some and None are both children of Option, your function signature just declares that you're returning an Option 
that contains some type (such as the Int type shown below).
```scala
object HelloWorld {
    
    def toInt(in: String): Option[Int] = {
        try {
            Some(Integer.parseInt(in.trim))
        } catch {
            case e: NumberFormatException => None
        }
    }
    
   def main(args: Array[String]) {
        println("Hello, world!" + toInt("77"))
        
        val bag = List("1", "2", "foo", "3", "bar")
        val sum = bag.flatMap(toInt).sum
        println("Sum:" + sum)
        
        toInt("88") match {
            case Some(i:Int) => println(i)
            case None => println("That didn't work.")
        }
        
        val list: List[Any] = List(toInt("99"), toInt("99xx"))
        list.foreach(x => x match {
            case Some(i) => println(i)
            case None => println("That didn't work.")
        })
   }
}
```
Run the codes above
```scala
$scalac *.scala
$scala HelloWorld
Hello, world!Some(77)
Sum:6
88
99
That didn't work.
```

### def/val
- **def**: define method
- **val**: define immutable variable
- **var**: define mutable variable
```scala
object HelloWorld {
    //Declaring methods
   //private[this]     method is only available to the current instance
   //private           method is available to the current instance and other instances of the same class
   //protected         method is only available to subclasses
   //private[model]    method is available to all classes beneath the com.acme.coolapp.model package
   //private[coolapp]  method is available to all classes beneath the com.acme.coolapp package
   //private[acme]     method is available to all classes beneath the com.acme package
   //(no modifier)     method is public
   
   // "normal" method definition
   private def add5(a: Int, b: Int) = a + b

   // same method, but with the return type declared
   protected def add6(a: Int, b: Int): Int = a + b

   // define the method body in a block (in curly braces)
   private[HelloWorld] def add7(a: Int, b: Int): Int = {
     a + b
   }
    
   def main(args: Array[String]) {
       println(".....")
       //define a function literal
       //(i: Int) => { i * 2 }
       //assign that function literal to a variable
       val double = (i: Int) => { i * 2 }
       
       //invoke double just like you’d call a method
       println(1)
       println(double(2))   // 4
       println(double(3))    // 6
       
       val list = List.range(1, 5)
       println(list.map(double)) //List(2, 4, 6, 8)
       
       //define function using four formats
       val f1: (Int) => Boolean = i => { i % 2 == 0 }
       val f2: Int => Boolean = i => { i % 2 == 0 }
       val f3: Int => Boolean = i => i % 2 == 0
       val f4: Int => Boolean = _ % 2 == 0
       println(f1(2)) //true
       println(f2(2)) //true
       println(f3(2)) //true
       println(f4(2)) //true
       
       // implicit approach
       val add1 = (x: Int, y: Int) => { x + y }
       val add2 = (x: Int, y: Int) => x + y
       // explicit approach
       val add3: (Int, Int) => Int = (x,y) => { x + y }
       val add4: (Int, Int) => Int = (x,y) => x + y
       println(add1(2, 3)) // 5
       println(add2(2, 3)) // 5
       println(add3(2, 3)) // 5
       println(add4(2, 3)) // 5
       
       //Assigning an existing function/method to a function variable
       val c1 = scala.math.cos _
       val c2 = scala.math.cos(_)
       println(c1(0)) // 1.0
       println(c2(0)) // 1.0
       val p = scala.math.pow(_, _)
       println(p(scala.math.E, 2)) // 7.3890560989306495
   }    
}
```

<div id="11">

### Object/Case Class/Trait
* *Object*  
简单说就是单例类(singleton class), 类似于Java中Enum，只存在一个实例。一个用处是封装一些与上下文无关的fields和methods，还有用处是作为其他class对象的创建工厂。参看[说明](https://en.wikibooks.org/wiki/Scala/Objects)
```bash
object HelloWorld {
   def main(args: Array[String]) {
      // 此处调用触发Hello类对象的创建和初始化(instantiation/initialization)
      // in Hello  --对象初始化先执行println
      // hi   --打印对象的field
      println(Hello.hi)  

      // 重复打印语句仍旧是全局的Hello对象
      // hi   --不会再次初始化，执行println
      println(Hello.hi)  
   }
}

object Hello {
    println("in Hello"); 
    def hi = "hi" 
}
```

* *Case Class*  
简单说作用类似于Java中Bean和DTO概念<br>
A [case class](https://data-flair.training/blogs/scala-case-class/) is an instantiable class that includes several automatically generated methods. 
Case classes work great for data transfer objects, the kind of classes that are mainly used for 
storing data, given the data-based methods that are generated.
```bash
// defined class Character
// 系统自动产生类实现方法
case class Character(name: String, isThief: Boolean)

object HelloWorld {
   def main(args: Array[String]) {
        // Here’s our companion object’s factory method, Character.apply()
        val h = Character("Hadrian", true)
        Console.println(h.name)  // Hadrian
        //The generated toString method
        println(h)  // Character(Hadrian,true)

        val r = h.copy(name = "Royce")
        println(r)  // Character(Royce,true)

        // the == operator triggers an instance’s equals method
        println(h == r) // false

        val judgment = h match {
            // The companion object’s unapply method allows us to 
            // decompose the instance into its parts, binding the first 
            // field (see Matching with Wildcard Patterns) and using 
            // a literal value to match the second field. 
            case Character(x, true) => s"$x is a thief"         
            case Character(x, false) => s"$x is not a thief"
       }
       println(judgment) //Hadrian is a thief
   }
}
```

* *Trait*  
简单说作用等同于Java中interface<br>
A trait is a kind of class that enables multiple inheritance. Classes, case classes, objects, and (yes) traits 
can all extend no more than one class but can extend multiple traits at the same time. 
Unlike the other types, however, traits cannot be instantiated.
Thus, a class defined as class D extends A with B with C, where A is a class 
and B and C are traits, would be reimplemented by the compiler as class D extends C extends B extends A.
The rightmost trait is the immediate parent of the class being defined, and either the class or the first trait becomes the last parent class.
```bash
trait HtmlUtils {
    def removeMarkup(input: String) = {
             input
               .replaceAll("""</?\w[^>]*>""","")
               .replaceAll("<.*>","")
    }
}

class Page(val s: String) extends HtmlUtils {
    def asPlainText = removeMarkup(s)
}

object Page {
    def main(args: Array[String]) {
        val result = new Page("<html><body><h1>Introduction</h1></body></html>").asPlainText
        println(result)  // Introduction
    }
}
```

<div id="111">

### Companion object
Scala中会将a class/trait和an object在同一个文件中定义成相同name, 但the object并不extend the trait/class. 伴生对象可以访问class和 trait中的private域和方法，如下代码示例
+ trait
```
trait Simple {
private def line = "Line"
}

object Simple {
val objTrait = new Simple{}
def lineObj=objTrait.line
}

Simple.lineObj
```
+ class
```
class Simple {
private def line = "Line"
}

object Simple {
val objTrait = new Simple{}
def lineObj=objTrait.line
}

Simple.lineObj
```

<div id="112">

### classOf/isInstanceOf/asInstanceOf
  Scala  | Java 
------------- | -------------
   obj.isInstanceOf[C]        |  obj instanceof C
   obj.asInstanceOf[C]        |  (C)obj
   classOf[C]        |  C.class

考虑这个function,把输入类型转化成List[Int], 或者一个更加复杂parameterized type如Map[Int, List[Int]].
```scala
object HelloWorld {
  def castToType[A](x: Any): A = {
    // throws if A is not the right type
    x.asInstanceOf[A]
  }

   def main(args: Array[String]) {
      val x = List(1, 2, 3)
      val y = castToType[List[String]](x)
      println(y)  // List(1, 2, 3)
      println(y(0))  // 1
   }
}
```

- p.isInstanceOf[C] 判断p是否为C对象的实例, 如果类型匹配则用p.asInstanceOf[C]把p转换成C对象的实例
- 如果没有用isInstanceOf先判断对象是否为指定类的实例,就直接用asInstanceOf转换则可能会抛出异常
- 如果对象是null,则isInstanceOf一定返回false,asInstanceOf一定返回null
- isInstanceOf只能判断出对象是否为指定类以及其子类的对象,而不能精确的判断对象就是指定类的对象
- 如果要求精确地判断出对象就是指定类的对象,就只能使用getClass和classOf 
- p.getClass和classOf[C]可以获取对象的类,然后使用==操作符即可判断是否匹配

```scala
import scala.reflect._

class HelloWorldAncestor {}
class HelloWorld extends HelloWorldAncestor
object HelloWorld{
    def main(args: Array[String]) {
        val p:HelloWorldAncestor = new HelloWorld
        //判断p是否为HelloWorldAncestor类的实例
        println(p.isInstanceOf[HelloWorldAncestor])//true
        //判断p的类型是否为HelloWorldAncestor类
        println(p.getClass == classOf[HelloWorldAncestor])//false
        //判断p的类型是否为HelloWorld类
        println(p.getClass == classOf[HelloWorld])//true
        
        val ct = classTag[HelloWorldAncestor]
        println(ct) // HelloWorldAncestor
        val ctc = ct.runtimeClass
        println(ctc) // class HelloWorldAncestor
        val c1 = ctc.asInstanceOf[Class[HelloWorldAncestor]]
        println(c1) // class HelloWorldAncestor
        val c2 = classOf[HelloWorldAncestor]
        println(c2) // class HelloWorldAncestor
        println(c1 == c2) // true
    }
}
```

## Scala Compile
* 编译文件的目录结构
```bash
// defined class Character
case class Character(name: String, isThief: Boolean)

object HelloWorld {
   def main(args: Array[String]) {
      val h = Character("Hadrian", true)
      Console.println(h.name)  // Hadrian
      println(h)  // Character(Hadrian,true)
   }
}

# 默认编译
/src $ scalac HelloWorld.scala
/src $ ls
Character$.class   Character.class    HelloWorld$.class  HelloWorld.class   HelloWorld.scala

# 指定编译输出目录
/src $ scalac -d bin HelloWorld.scala 
scalac error: bin does not exist or is not a directory
/src $ mkdir bin
/src $ scalac -d bin HelloWorld.scala 
/src $ ls bin/
Character$.class   Character.class    HelloWorld$.class  HelloWorld.class

# 运行
/src $ scala -cp bin HelloWorld
Hello, world !
Hadrian
Character(Hadrian,true)
```
如果编译碰到`Caused by: java.lang.ClassNotFoundException: scala.Product$class`，表明使用的lib所对应的Scala版本与当前shell中Scala版本号不一致，需要用下面命令查看版本号，然后使用对应编译版本的lib。

* 查看版本信息
```
scala> util.Properties.versionString
res0: String = version 2.12.8

scala> util.Properties.versionMsg
res1: String = Scala library version 2.12.8 -- Copyright 2002-2018, LAMP/EPFL and Lightbend, Inc.
```

* SBT

sbt是Scala的工程编译工具，类似make/Maven，能够帮助解决编译依赖等问题。

sbt需要手动安装，如下是Ubuntu系统安装步骤
```
echo "deb https://dl.bintray.com/sbt/debian /" | sudo tee -a /etc/apt/sources.list.d/sbt.list
sudo apt-key adv --keyserver hkp://keyserver.ubuntu.com:80 --recv 2EE0EA64E40A89B84B2DF73499E82A75642AC823
sudo apt-get update
sudo apt-get install sbt
```
其他安装方式参考[文档](https://www.scala-sbt.org/1.0/docs/Installing-sbt-on-Linux.html)

安装完成后就可以编译和执行了
```
$ touch build.sbt
$ sbt
# 支持tab键自动提示
sbt:griffin> clean
sbt:griffin> compile
sbt:griffin> run
# 修改build.sbt后刷新项目设定
sbt:griffin> reload
```
sbt使用example可以参考[文档](https://www.scala-sbt.org/1.0/docs/sbt-by-example.html)

- 如果遇到编译错误，例如dependency错误, scalaj.http包找不到
```
import scalaj.http._

object HttpUtil {
    def main(args: Array[String]): Unit = {
        println("Hello, world!")
  }
}
```
需要修改`build.sbt`工程文件，加入依赖声明
```
# 依赖对应的pom文件地址 
# https://repo1.maven.org/maven2/org/scalaj/scalaj-http_2.12/2.4.0/scalaj-http_2.12-2.4.0.pom
libraryDependencies += "org.scalaj" % "scalaj-http_2.12" % "2.4.0"
libraryDependencies += "org.apache.httpcomponents" % "httpclient" % "4.5.7"
```
sbt manage dependencies操作参考[文档](https://alvinalexander.com/scala/sbt-how-to-manage-project-dependencies-in-scala)

- 如果遇到下载timeout，可能是proxy设定问题, 需要导出jvm参数变量
```
export JAVA_OPTS="$JAVA_OPTS -Dhttp.proxyHost=proxy.server -Dhttp.proxyPort=3328 -Dhttps.proxyHost=proxy.server
```
proxy设定方式参考[文档](http://www.alternatestack.com/uncategorized/sbt-behind-proxy/)

- 如果遇到下载scala.MatchError，一般都是case语句条件没有匹配，需要检查代码
```scala
scala.MatchError: org.apache.hadoop.fs.FsUrlConnection:http://127.0.0.1:9200/griffin/accuracy (of class org.apache.hadoop.fs.FsUrlConnection)
    at scalaj.http.HttpRequest.scalaj$http$HttpRequest$$doConnection(Http.scala:343)
    at scalaj.http.HttpRequest.exec(Http.scala:335)

    proxyConfig.map(urlToFetch.openConnection).getOrElse(urlToFetch.openConnection) match {
      case conn: HttpURLConnection =>
        ...
    }    
```

* [Scalac](https://www.scala-lang.org/files/archive/nightly/docs-2.10.2/manual/html/scalac.html)
* 使用-Xshow-phases, Scala编译器可以输出代码的编译过程
```bash
$ scalac -Xshow-phases *.scala
             phase name  id  description
             ----------  --  -----------
                 parser   1  parse source into ASTs, perform simple desugaring
                  namer   2  resolve names, attach symbols to named trees
         packageobjects   3  load package objects
                  typer   4  the meat and potatoes: type the trees
                 patmat   5  translate match expressions
         superaccessors   6  add super accessors in traits and nested classes
             extmethods   7  add extension methods for inline classes
                pickler   8  serialize symbol tables
              refchecks   9  reference/override checking, translate nested objects
                uncurry  10  uncurry, translate function values to anonymous classes
              tailcalls  11  replace tail calls by jumps
             specialize  12  @specialized-driven class and method specialization
          explicitouter  13  this refs to outer pointers, translate patterns
                erasure  14  erase types, add interfaces for traits
            posterasure  15  clean up erased inline classes
               lazyvals  16  allocate bitmaps, translate lazy vals into lazified defs
             lambdalift  17  move nested functions to top level
           constructors  18  move field definitions into constructors
                flatten  19  eliminate inner classes
                  mixin  20  mixin composition
                cleanup  21  platform-specific cleanups, generate reflective calls
                  icode  22  generate portable intermediate code
                inliner  23  optimization: do inlining
inlineExceptionHandlers  24  optimization: inline exception handlers
               closelim  25  optimization: eliminate uncalled closures
                    dce  26  optimization: eliminate dead code
                    jvm  27  generate JVM bytecode
               terminal  28  The last phase in the compiler chain
```

## Scala Samples
- [http rest](./sample/es.scala)
- [wrap long line](./sample/wrap-line.scala)

    