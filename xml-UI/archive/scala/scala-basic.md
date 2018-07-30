- [Basic Language Concepts](#basic-language-concepts)
    - [Null/Nil/Nothing/Unit](#nullnilnothingunit)
    - [Option/Some/None](#optionsomenone)
    - [Def/Val](#defval)
    - [classOf/isInstanceOf/asInstanceOf](#classofisinstanceofasinstanceof)
    - [String](#string)
    - [Head](scala-head.md)
    - [Collection](scala-collections.md)
- [Scala Compile](#scala-compile)
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
* 使用scalac -Xprint:cleanup <Name>.scala, Scala编译器可以输出代码的抽象语法树(AST)