- [Basic Language Concepts](#Basic-Language-Concepts)
    - [Null](#null)
    - [String](#string)

***

## Basic Language Concepts

### Null
Null, null, Nil, Nothing, None, and Unit in Scala
```scala
Null每 It's a Trait.
null每 Its an instance of Null- Similar to Java null.
Nothing is a Trait. It's a subtype of everything. But not superclass of anything. There are no instances of Nothing.
// List of Strings
val fruit: List[String] = List("apples", "oranges", "pears")
// List of Integers
val nums: List[Int] = List(1, 2, 3, 4)
// Empty List.
val empty: List[Nothing] = List()

Nil每 Represents an empty List of anything of zero length. It's not that it refers to nothing but it refers to List which has no contents.
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

None每 Used to represent a sensible return value. Just to avoid null pointer exception. Option has exactly 2 subclasses- Some and None. None signifies no result from the method.
Unit每 Type of method that doesn＊t return a value of anys sort.
``` 

### String
```scala
val name = "James"
println(s"Hello, $name")  // Hello, James
==
StringContext("Hello, ", "").s(name)
```
