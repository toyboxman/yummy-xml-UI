***

## Ways to use collections
+ [Collection��ʹ��](https://www.javatpoint.com/collections-in-java)
+ [Collections in Java](https://howtodoinjava.com/java-collections/)
+ [HashMap��7�ֱ�����ʽ](https://mp.weixin.qq.com/s/v518GDzKRZQUaVelgpltKQ)
    + [Filter a Map by keys and values](https://beginnersbook.com/2017/10/java-8-filter-a-map-by-keys-and-values/)
    + [List Static Initialization](https://www.baeldung.com/java-init-list-one-line)
+ [Map to String Conversion](https://www.baeldung.com/java-map-to-string-conversion)
+ [Java 8 Stream](https://www.javatpoint.com/java-8-stream)       
+ [Java Stream Filter](https://www.javatpoint.com/java-8-stream-filter)

### ��stream/map��ʽת����map��������
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
ִ�н��
```
Node{id=1, name=Null}
Node{id=2, name=Null}
Node{id=3, name=Null}
```
toMapʱ��Ҫ�������key�ظ�����������л��쳣��ֹ������쳣û�в���ͻ����ҳ�ԭ��
```java 
ArrayList<String[]> list = new ArrayList<>();
list.add(new String[]{"key1", "2"});
list.add(new String[]{"key1", "3"});
list.add(new String[]{"key2", "3"});
list.stream().collect(Collectors.toMap(v -> v[0], v -> v[1]));
```
��Ϊ`key1`�������ε���ת�ƹ���ʧ��
```sh
Exception in thread "main" java.lang.IllegalStateException: Duplicate key 2
	at java.util.stream.Collectors.lambda$throwingMerger$114(Collectors.java:133)
	at java.util.stream.Collectors$$Lambda$3/2108649164.apply(Unknown Source)
	at java.util.HashMap.merge(HashMap.java:1245)
	at java.util.stream.Collectors.lambda$toMap$172(Collectors.java:1320)
```    

### ��stream/map��ʽת��list������
```java 
static class Node {
    private int id;
    private String name;

    public Node(int id) {
        this.id = id;
    }
    
    // �˴���������ԭ����stream().map(id -> new Node(id).setName("Node-" + id))
    // ��Ҫһ����ʽ�ļ���Ԫ�ص�type������ͻ���ʾvoid��ƥ�伯��Ԫ������
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
// �����ø��򻯵ĺ������Ƶ������lamda
// Nodes.forEach(System.out::println);
``` 
ִ�н��
```
Node{id=1, name=Node-1}
Node{id=2, name=Node-2}
Node{id=3, name=Node-3}
```

### ��stream/map��ʽ������ֵ
```java 
int[] array = new int[]{1,2,3,-4,5};

// ����������������������֮��
Arrays.stream(array).mapToInt(i -> i < 0 ? -1 : 1).count();
// ��� 4
System.out.println(n)
```