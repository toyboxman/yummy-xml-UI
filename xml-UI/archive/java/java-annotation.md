***

## Usual annotation
- annotation custom[[***1***](https://www.baeldung.com/java-custom-annotation), [***2***](https://www.javatpoint.com/java-annotation)]
- [annotationÇý¶¯±à³Ì](https://mp.weixin.qq.com/s/MyOm8-9ScO3bXJFgyr69Qw)

### javax.annotation.concurrent.ThreadSafe
Does this annotation actually make the class Thread Safe or is it just for readability
```java 
@ThreadSafe 
public class A {

}

//Place this annotation on methods that can safely be called from more than one thread concurrently. 
//The method implementer must ensure thread safety using a variety of possible techniques 
//including immutable data, synchronized shared data, or not using any shared data at all.
``` 
It does not make the class Thread Safe, the programmer does it Thread Safe and adds the annotation


