***

## Ways to use collections
+ [Collection��ʹ��](https://www.javatpoint.com/collections-in-java)
+ [Collections in Java](https://howtodoinjava.com/java-collections/)
+ [HashMap��7�ֱ�����ʽ](https://mp.weixin.qq.com/s/v518GDzKRZQUaVelgpltKQ)
+ [Map to String Conversion](https://www.baeldung.com/java-map-to-string-conversion)
+ [Java 8 Stream](https://www.javatpoint.com/java-8-stream)       
+ [Java Stream Filter](https://www.javatpoint.com/java-8-stream-filter)

### ��stream/map��ʽת��list������
```java 
static class node {
    private int id;
    private String name;

    public node(int id) {
        this.id = id;
    }
    
    // �˴���������ԭ����stream().map(id -> new node(id).setName("node-" + id))
    // ��Ҫһ����ʽ�ļ���Ԫ�ص�type������ͻ���ʾvoid��ƥ�伯��Ԫ������
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
// �����ø��򻯵ĺ������Ƶ������lamda
// nodes.forEach(System.out::println);
``` 
ִ�н��
```
node{id=1, name=node-1}
node{id=2, name=node-2}
node{id=3, name=node-3}
```

