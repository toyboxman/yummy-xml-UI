***

## Ways to use collections
+ [Collection的使用](https://www.javatpoint.com/collections-in-java)
+ [Collections in Java](https://howtodoinjava.com/java-collections/)
+ [HashMap的7种遍历方式](https://mp.weixin.qq.com/s/v518GDzKRZQUaVelgpltKQ)
    + [Filter a Map by keys and values](https://beginnersbook.com/2017/10/java-8-filter-a-map-by-keys-and-values/)
    + [List Static Initialization](https://www.baeldung.com/java-init-list-one-line)
+ [Map to String Conversion](https://www.baeldung.com/java-map-to-string-conversion)
+ [Java 8 Stream](https://www.javatpoint.com/java-8-stream)       
+ [Java Stream Filter](https://www.javatpoint.com/java-8-stream-filter)

### 用stream/map方式转换成map类型数据
```java 
static class Node {
    private int id;
    private String name;

    public Node(int id) {
        this.id = id;
    }

    public Node setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public String toString() {
        return "Node{" + "id=" + id + ", name=" + name + '}';
    }
}
    
Map<Integer, Node> nmap = new HashMap<>();
nmap.put(1, new Node(1));
nmap.put(2, new Node(2));
nmap.put(3, new Node(3));
Map<Integer, String> map = nmap.entrySet().stream()
        .collect(Collectors.toMap(
            entry -> entry.getKey(),
            entry -> entry.getValue().toString(),
        ));
map.entrySet().forEach(System.out::println);
``` 
执行结果
```
Node{id=1, name=Null}
Node{id=2, name=Null}
Node{id=3, name=Null}
```

### 用stream/map方式转换list中数据
```java 
static class Node {
    private int id;
    private String name;

    public Node(int id) {
        this.id = id;
    }
    
    // 此处返回类型原因是stream().map(id -> new Node(id).setName("Node-" + id))
    // 需要一个显式的集合元素的type，否则就会提示void不匹配集合元素类型
    public Node setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public String toString() {
        return "Node{" + "id=" + id + ", name=" + name + '}';
    }
}
    
List<Integer> ids = new ArrayList<>();
ids.add(1);
ids.add(2);
ids.add(3);
List<Node> Nodes = ids.stream()
        .map(id -> new Node(id).setName("Node-" + id))
        .collect(Collectors.toList());
Nodes.forEach(n -> System.out.println(n));
// 或者用更简化的函数名称调用替代lamda
// Nodes.forEach(System.out::println);
``` 
执行结果
```
Node{id=1, name=Node-1}
Node{id=2, name=Node-2}
Node{id=3, name=Node-3}
```

### 用stream/map方式计算数值
```java 
int[] array = new int[]{1,2,3,-4,5};

// 计算数组中正数负数个数之差
Arrays.stream(array).mapToInt(i -> i < 0 ? -1 : 1).count();
// 输出 4
System.out.println(n)
```