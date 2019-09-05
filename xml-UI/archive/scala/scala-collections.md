***

## Use Collection
Scala中集合类型关系图如下
![Image of Collection](https://i.stack.imgur.com/2fjoA.png)

+ [Seq vs List](https://stackoverflow.com/questions/10866639/difference-between-a-seq-and-a-list-in-scala)

站在Java角度, Scala's Seq(trait)就是Java's List接口, Scala's List(abstract class)就是Java's LinkedList实现, 但Scala's List是immutable, 此点与LinkedList不同. Seq是列数组类型的一个很好泛化接口, 有三种类型: *collection.Seq*, *collection.mutable.Seq*和*collection.immutable.Seq*, 最后一种是"default"使用类型. 还有*GenSeq*和*ParSeq*类型, 后者方法是做parallel处理, 前者是所有Seq, ParSeq的父类型.
    

### Array/List/Set/Tuple/Map
```scala
object HelloWorld {
   println("Hello, world")
   
   def main(args: Array[String]) {
       //Arrays preserve order, can contain duplicates, and are mutable
      val array = Array(1, 2, 3, 4, 5, 1, 2, 3, 4, 5)
      println(array(0))
      
      //Lists preserve order, can contain duplicates, and are immutable.
      val list = List(1, 2, 3, 4, 5, 1, 2, 3, 4, 5)
      println(list(3))
      
      //Sets do not preserve order and have no duplicates
      val set = Set(1, 2, 3, 4, 5, 1, 2, 3, 4, 5)
      println(set.size)  // size is 5
      
      //A tuple groups together simple logical collections of items without using a class.
      val tuple = ("localhost", 80)
      //Unlike case classes, they don’t have named accessors, instead they have accessors 
      //that are named by their position and is 1-based rather than 0-based
      println(tuple._1)  // localhost
      println(tuple._2)  // 80
      
      val tuple_1 = ("remotehost", 80)
      
      tuple match {
        case ("localhost", port) => println("validated")
        case (host, port) => println("Any")
      }
      
      tuple_1 match {
        case ("localhost", port) => println("validated")
        case _ => println("Any")
      }
      
      val tuple_2 = 1 -> 2
      println(tuple_2)
      
      //Maps
      val map_1 = Map(1 -> 2)
      println(map_1)
      val map_2 = Map("foo" -> "bar")
      println(map_2.get("foo"))
      
      val map_3 = Map(1 -> "one", 2 -> "two")
      val map_4 = Map((1, "one"), (2, "two"))
      println(map_3 == map_4)  // true, map_3 equal to map_4
      
      //Maps can themselves contain Maps or even functions as values.
      val map_5 = Map(1 -> Map("foo" -> "bar"))
      println(map_5.get(1).get("foo"))
      val map_6 = Map("timesTwo" -> { "function" })
      val prt = map_6.get("timesTwo")
      println(prt)
      
   }
}
```
Run the codes
```scala
$scalac *.scala
$scala HelloWorld
Hello, world
1
4
5
localhost
80
validated
Any
(1,2)
Map(1 -> 2)
Some(bar)
true
bar
Some(function)
```

### Functional Combinators
* map
* range
* foreach
* filter
* zip
* partition
* find
* drop
* dropWhile
* foldLeft
* foldRight
* flatten
* flatMap
```scala
object HelloWorld {
   def main(args: Array[String]) {
        //map function
        //Evaluates a function over each element in the list, returning a list with the same number of elements.
        val list = List(1, 2, 3, 4)
        val list_1 = list.map((i: Int) => i * 2)
        println(list_1) //List(2, 4, 6, 8)
        
        def triple(i: Int): Int = i * 3
        val list_2 = list.map(triple)
        println(list_2) //List(3, 6, 9, 12)
        
        val double = (i: Int) => { i * 2 }
        println(double(5)) //10
        
        val list_3 = List.range(5, 10)
        println(list_3.map(double)) //List(10, 12, 14, 16, 18)
        
        //foreach is like map but returns nothing.
        //foreach is intended for side-effects only.
        val doubledList = List.range(6, 9)
        val result = doubledList.foreach((i: Int) => println(i * 2))
        println(result)  //return nothing, only ()
        println(doubledList) //List(6, 7, 8)
        
        //filter removes any elements where the function you pass in evaluates to false. Functions that return a Boolean are often called predicate functions
        val list_4 = List.range(5, 10)
        val list_4_1= list_4.filter((i: Int) => i % 2 == 0) //keep even
        println(list_4_1) //List(6, 8)
        
        //zip aggregates the contents of two lists into a single list of pairs.
        val list_5 = List(1, 2, 3).zip(List("a", "b", "c"))
        println(list_5) //List((1,a), (2,b), (3,c))
        
        //partition splits a list based on where it falls with respect to a predicate function.
        val list_6 = List.range(1, 11)
        val list_6_1 = list_6.partition(_ % 2 == 0)
        println(list_6_1) //(List(2, 4, 6, 8, 10),List(1, 3, 5, 7, 9))
        
        //find returns the first element of a collection that matches a predicate function.
        val va = list_6.find((i: Int) => i > 5)
        println(va)  //Some(6) fist element in list on condition
        
        //drop drops the first i elements
        val list_6_drop = list_6.drop(6)
        println(list_6) //List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        println(list_6_drop) //List(7, 8, 9, 10)
        
        //dropWhile removes the first element that match a predicate function
        val list_6_dropwhile = list_6.dropWhile(_ % 5 != 0)
        println(list_6_dropwhile) //List(5, 6, 7, 8, 9, 10)
        
        //foldLeft
        //0 is the starting value (Remember that numbers is a List[Int]), and m acts as an accumulator.
        val list_7 = List.range(2, 5)
        val list_7_1 = list_7.foldLeft(0)((m: Int, n: Int) => m + n)
        println(list_7_1) // 9
        //m: 0 n: 2
        //m: 2 n: 3
        //m: 5 n: 4
        val list_7_2 = list_7.foldLeft(0) { (m: Int, n: Int) => println("m: " + m + " n: " + n); m + n }
        
        //foldRight Is the same as foldLeft except it runs in the opposite direction.
        //m: 4 n: 0
        //m: 3 n: 4
        //m: 2 n: 7
        val list_7_3 = list_7.foldRight(0) { (m: Int, n: Int) => println("m: " + m + " n: " + n); m + n }
        println(list_7_3) // 9
        
        //flatten collapses one level of nested structure.
        val list_8 = List(List(1, 2), List(3, 4)).flatten
        println(list_8) //List(1, 2, 3, 4)
        
        //flatMap is a frequently used combinator that combines mapping and flattening. flatMap takes a function that works on the nested lists and then concatenates the results back together.
        val list_9 = List(List(1, 2), List(3, 4))
        val list_9_flat = list_9.flatMap(x => x.map(_ * 2))
        println(list_9_flat) //List(2, 4, 6, 8)
        //equal to a bit more codes below
        val list_9_map = list_9.map((x: List[Int]) => x.map(_ * 2)).flatten
        println(list_9_map) //List(2, 4, 6, 8)
        
        //Generalized functional combinators
        val list_10 = List.range(5, 8) //List(5, 6, 7)
        def ourMap(numbers: List[Int], fn: Int => Int): List[Int] = {
            numbers.foldRight(List[Int]()) { 
                (x: Int, xs: List[Int]) => fn(x) :: xs
            }
        }
        val list_10_1 = ourMap(list_10, double(_))
        println(list_10_1) //List(10, 12, 14)
        
        //Map function
        val extensions = Map("steve" -> 100, "bob" -> 101, "joe" -> 201)
        val ext_map = extensions.filter((namePhone: (String, Int)) => namePhone._2 < 200)
        println(ext_map) //Map(steve -> 100, bob -> 101)
        
   }
}
``` 