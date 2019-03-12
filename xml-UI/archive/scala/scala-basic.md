- [Basic Language Concepts](#basic-language-concepts)
    - [Object/Case Class/Trait](#objectcase-classtrait)
    - [Null/Nil/Nothing/Unit](#nullnilnothingunit)
    - [Option/Some/None](#optionsomenone)
    - [Def/Val](#defval)
    - [classOf/isInstanceOf/asInstanceOf](#classofisinstanceofasinstanceof)
    - [String](#string)
    - [Head](scala-head.md)
    - [Collection](scala-collections.md)
- [Scala Compile](#scala-compile)
- [Samples](#scala-samples)
***

## Basic Language Concepts

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
- **val**: define variable
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
       
       //invoke double just like you��d call a method
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

### classOf/isInstanceOf/asInstanceOf
  Scala  | Java 
------------- | -------------
   obj.isInstanceOf[C]        |  obj instanceof C
   obj.asInstanceOf[C]        |  (C)obj
   classOf[C]        |  C.class

�������function,����������ת����List[Int], ����һ�����Ӹ���parameterized type��Map[Int, List[Int]].
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

- p.isInstanceOf[C] �ж�p�Ƿ�ΪC�����ʵ��, �������ƥ������p.asInstanceOf[C]��pת����C�����ʵ��
- ���û����isInstanceOf���ж϶����Ƿ�Ϊָ�����ʵ��,��ֱ����asInstanceOfת������ܻ��׳��쳣
- ���������null,��isInstanceOfһ������false,asInstanceOfһ������null
- isInstanceOfֻ���жϳ������Ƿ�Ϊָ�����Լ�������Ķ���,�����ܾ�ȷ���ж϶������ָ����Ķ���
- ���Ҫ��ȷ���жϳ��������ָ����Ķ���,��ֻ��ʹ��getClass��classOf 
- p.getClass��classOf[C]���Ի�ȡ�������,Ȼ��ʹ��==�����������ж��Ƿ�ƥ��

```scala
import scala.reflect._

class HelloWorldAncestor {}
class HelloWorld extends HelloWorldAncestor
object HelloWorld{
    def main(args: Array[String]) {
        val p:HelloWorldAncestor = new HelloWorld
        //�ж�p�Ƿ�ΪHelloWorldAncestor���ʵ��
        println(p.isInstanceOf[HelloWorldAncestor])//true
        //�ж�p�������Ƿ�ΪHelloWorldAncestor��
        println(p.getClass == classOf[HelloWorldAncestor])//false
        //�ж�p�������Ƿ�ΪHelloWorld��
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

### Object/Case Class/Trait
* *Object*  
��˵���ǵ�����,������Java��Enum.��һ�������Ƕ���static��method��fieldֻ�ܷ���object��<br>
An object is a type of class that can have no more than one instance,known in object-oriented design as a singleton. 
```bash
object HelloWorld {
   def main(args: Array[String]) {
      // this call will trigger Hello instantiation/initialization
      println(Hello.hi)  // in Hello
                     // hi
      
      // Repeating the call to the object's ��hi��method reused the same global instance 
      // so there was no additional initialization
      println(Hello.hi)  // hi
   }
}

object Hello { 
    // This println at the top level of the object is invoked at instantiation/initialization,
    // which only occurs when it is accessed for the first time
    println("in Hello"); 
    def hi = "hi" 
}
```

* *Case Class*  
��˵����������Java��Bean��DTO����<br>
A case class is an instantiable class that includes several automatically generated methods. 
Case classes work great for data transfer objects, the kind of classes that are mainly used for 
storing data, given the data-based methods that are generated.
```bash
// defined class Character
// ϵͳ�Զ�������ʵ�ַ���
case class Character(name: String, isThief: Boolean)

object HelloWorld {
   def main(args: Array[String]) {
        // Here��s our companion object��s factory method, Character.apply()
        val h = Character("Hadrian", true)
        Console.println(h.name)  // Hadrian
        //The generated toString method
        println(h)  // Character(Hadrian,true)

        val r = h.copy(name = "Royce")
        println(r)  // Character(Royce,true)

        // the == operator triggers an instance��s equals method
        println(h == r) // false

        val judgment = h match {
            // The companion object��s unapply method allows us to 
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
��˵���õ�ͬ��Java��interface<br>
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

## Scala Compile
* �����ļ���Ŀ¼�ṹ
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

# Ĭ�ϱ���
/src $ scalac HelloWorld.scala
/src $ ls
Character$.class   Character.class    HelloWorld$.class  HelloWorld.class   HelloWorld.scala

# ָ���������Ŀ¼
/src $ scalac -d bin HelloWorld.scala 
scalac error: bin does not exist or is not a directory
/src $ mkdir bin
/src $ scalac -d bin HelloWorld.scala 
/src $ ls bin/
Character$.class   Character.class    HelloWorld$.class  HelloWorld.class

# ����
/src $ scala -cp bin HelloWorld
Hello, world !
Hadrian
Character(Hadrian,true)
```
�����������`Caused by: java.lang.ClassNotFoundException: scala.Product$class`������ʹ�õ�lib����Ӧ��Scala�汾�뵱ǰshell��Scala�汾�Ų�һ�£���Ҫ����������鿴�汾�ţ�Ȼ��ʹ�ö�Ӧ����汾��lib��

* �鿴�汾��Ϣ
```
scala> util.Properties.versionString
res0: String = version 2.12.8

scala> util.Properties.versionMsg
res1: String = Scala library version 2.12.8 -- Copyright 2002-2018, LAMP/EPFL and Lightbend, Inc.
```

* SBT

sbt��Scala�Ĺ��̱��빤�ߣ�����make/Maven���ܹ���������������������⡣

sbt��Ҫ�ֶ���װ��������Ubuntuϵͳ��װ����
```
echo "deb https://dl.bintray.com/sbt/debian /" | sudo tee -a /etc/apt/sources.list.d/sbt.list
sudo apt-key adv --keyserver hkp://keyserver.ubuntu.com:80 --recv 2EE0EA64E40A89B84B2DF73499E82A75642AC823
sudo apt-get update
sudo apt-get install sbt
```
������װ��ʽ�ο�[�ĵ�](https://www.scala-sbt.org/1.0/docs/Installing-sbt-on-Linux.html)

��װ��ɺ�Ϳ��Ա����ִ����
```
$ touch build.sbt
$ sbt
# ֧��tab���Զ���ʾ
sbt:griffin> clean
sbt:griffin> compile
sbt:griffin> run
# �޸�build.sbt��ˢ����Ŀ�趨
sbt:griffin> reload
```
sbtʹ��example���Բο�[�ĵ�](https://www.scala-sbt.org/1.0/docs/sbt-by-example.html)

- ������������������dependency����, scalaj.http���Ҳ���
```
import scalaj.http._

object HttpUtil {
    def main(args: Array[String]): Unit = {
        println("Hello, world!")
  }
}
```
��Ҫ�޸�`build.sbt`�����ļ���������������
```
# ������Ӧ��pom�ļ���ַ 
# https://repo1.maven.org/maven2/org/scalaj/scalaj-http_2.7.7/0.2.9/scalaj-http_2.7.7-0.2.9.pom
libraryDependencies += "org.scalaj" % "scalaj-http_2.7.7" % "0.2.9"
```
sbt manage dependencies�����ο�[�ĵ�](https://alvinalexander.com/scala/sbt-how-to-manage-project-dependencies-in-scala)

- �����������timeout��������proxy�趨����, ��Ҫ����jvm��������
```
export JAVA_OPTS="$JAVA_OPTS -Dhttp.proxyHost=proxy.vmware.com -Dhttp.proxyPort=3128 -Dhttps.proxyHost=proxy.vmware.com
```
proxy�趨��ʽ�ο�[�ĵ�](http://www.alternatestack.com/uncategorized/sbt-behind-proxy/)

* [Scalac](https://www.scala-lang.org/files/archive/nightly/docs-2.10.2/manual/html/scalac.html)
* ʹ��-Xshow-phases, Scala�����������������ı������
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