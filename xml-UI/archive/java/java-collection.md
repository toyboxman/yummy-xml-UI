***

## Ways to use collections
+ [Collection的使用](https://www.javatpoint.com/collections-in-java)
+ [Collections in Java](https://howtodoinjava.com/java-collections/)
+ [HashMap的7种遍历方式](https://mp.weixin.qq.com/s/v518GDzKRZQUaVelgpltKQ)
+ [Map to String Conversion](https://www.baeldung.com/java-map-to-string-conversion)
+ [Java 8 Stream](https://www.javatpoint.com/java-8-stream)       
+ [Java Stream Filter](https://www.javatpoint.com/java-8-stream-filter)

### 用stream/map方式转换list中数据
```java 
static class node {
    private int id;
    private String name;

    public node(int id) {
        this.id = id;
    }
    
    // 此处返回类型原因是stream().map(id -> new node(id).setName("node-" + id))
    // 需要一个显式的集合元素的type，否则就会提示void不匹配集合元素类型
    public node setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public String toString() {
        return "node{" + "id=" + id + ", name=" + name + '}';
    }
}
    
List<Integer> ids = new ArrayList<>();
ids.add(1);
ids.add(2);
ids.add(3);
List<node> nodes = ids.stream()
        .map(id -> new node(id).setName("node-" + id))
        .collect(Collectors.toList());
nodes.forEach(n -> System.out.println(n));
// 或者用更简化的函数名称调用替代lamda
// nodes.forEach(System.out::println);
``` 
执行结果
```
node{id=1, name=node-1}
node{id=2, name=node-2}
node{id=3, name=node-3}
```

