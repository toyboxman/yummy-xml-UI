***

## Ways to json processing
[Jackson Project](https://github.com/FasterXML/jackson)是一个流行的JSON library for Java, 可以做JSON数据parser/generator, POJO对象与JSON数据互相转换，以及流行数据格式处理如Avro, BSON, CBOR, CSV, Smile, (Java) Properties, Protobuf, XML or YAML
+ [jackson-annotations使用介绍](https://github.com/FasterXML/jackson-annotations)
+ [jackson-databind使用介绍](https://github.com/FasterXML/jackson-databind)
+ [jackson-core使用介绍](https://github.com/FasterXML/jackson-core)
+ [mapping动态变化json数据](https://www.baeldung.com/jackson-mapping-dynamic-object)
+ [json数据多个值mapping到一个对象field](https://www.baeldung.com/json-multiple-fields-single-java-field)
+ [json一个值mapping到一个对象多个field](#js2)
+ [jackson tree node结构使用介绍](https://www.baeldung.com/jackson-json-node-tree-model)
+ [结合ST将yaml数据导出生成文件](#js2st)

### 1.use com.fasterxml.jackson
- 由String构造JsonNode
```java 
ObjectMapper mapper = new ObjectMapper();
JsonNode sliceNode = mapper.readTree(new String(childData.getData()));
String author = sliceNode.get(AUTHOR).asText();
long timestamp = sliceNode.get(TIMESTAMP).asLong();
ArrayNode slice = (ArrayNode) sliceNode.get(SLICE);
for (JsonNode jsonNode : slice) {
    String uuid = jsonNode.get(NODE_UUID).asText();
    byte[] occupation = jsonNode.get(OCCUPATION).binaryValue();
    BitSet assignment = BitSet.valueOf(occupation);
}
``` 
- 创建JsonNode导出String
```java
ObjectMapper mapper = new ObjectMapper();
//创建json的根节点
ObjectNode rootNode = mapper.createObjectNode();
rootNode.put(AUTHOR, getAuthor().toString());
rootNode.put(TIMESTAMP, getTimestamp());
//根节点下创建一个数组型子节点
ArrayNode sliceNode = rootNode.putArray(SLICE);
for (Assignment node : AssignmentList) {
    ObjectNode slice = mapper.createObjectNode();
    slice.put(NODE_UUID, node.getUuid().toString());
    slice.put(NODE_IP, nodeMap.get(node.getUuid()));
    slice.put(SLICE_COUNT, node.getCount());
    //对bytes数据处理输出BASE64格式String,防止通过网络传输出现错误
    slice.put(OCCUPATION, BinaryNode.valueOf(node.getAssignment().toByteArray()).asText());
    sliceNode.add(slice);
}
String jsonString = null;
try {
    jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(rootNode);
} catch (Exception e) {
}
```
- 将json String转换成pojo对象
```java
ObjectMapper mapper = new ObjectMapper();
try {
    TypeFactory typeFactory = mapper.getTypeFactory();
    //按照集合类型读出pojo list
    CollectionType collectionType = typeFactory.constructCollectionType(List.class, TracerPojo.class);
    List<TracerPojo> list = mapper.readValue(vertical_configs.getValue().toString(), collectionType);
    //按照集合类型读出pojo list
    TypeReference<List<TracerPojo>> typeReference = new TypeReference<List<TracerPojo>>() {};
    list = mapper.readValue(json, typeReference);
} catch (IOException e) {
    e.printStackTrace();
}
```
- 将yaml数据转换成pojo对象
```yaml
- martin:
    name: Martin D'vloper
    job: Developer
    skills:
      - python
      - perl
      - pascal
- tabitha:
    name: Tabitha Bitumen
    job: Developer
    skills:
      - lisp
      - fortran
      - erlang
```
```java
public class YamlConvert {
    public static void main(String[] args) throws IOException {
        YAMLFactory yamlFactory = new YAMLFactory();
        ObjectMapper mapper = new ObjectMapper();
        YAMLParser parser = yamlFactory.createParser(new File("person.yaml"));
        TreeNode treeNode = mapper.readTree(parser);
        //直接转换成集合数据，得到数据是map的list
        //martin -> {LinkedHashMap@995}  size = 3
            //name -> Martin D'vloper, ...
        //tabitha -> {LinkedHashMap@1000}  size = 3
        //由于NodeBean没有直接可以映射到 martin ->， 因此无法直接得到List<NodeBean>
        List list = mapper.treeToValue(treeNode, List.class);

        if (treeNode.isContainerNode()) {
            for (int i = 0; i < treeNode.size(); i++) {
                TreeNode node = treeNode.get(i);
                // node.fieldNames().next()得到 martin
                // node.get(node.fieldNames().next())得到value
                // {"name":"Martin D'vloper","job":"Developer","skills":["python","perl","pascal"]}
                // 上面的json数据能被映射成NodeBean
                NodeBean bean = mapper.treeToValue(node.get(node.fieldNames().next()), NodeBean.class);
                System.out.println(bean);
            }
        }
    }

    public static class NodeBean {
        public String job;
        public String name;
        public List<String> skills;

        public NodeBean() {
        }

        @Override
        public String toString() {
            return "NodeBean{" +
                    "job='" + job + '\'' +
                    ", name='" + name + '\'' +
                    ", skills=" + skills +
                    '}';
        }
    }
}
```
执行结果
```
NodeBean{job='Developer', name='Martin D'vloper', skills=[python, perl, pascal]}
NodeBean{job='Developer', name='Tabitha Bitumen', skills=[lisp, fortran, erlang]}
```
如果把yaml文件修改一下，注释这两个key !martin， !tabitha
```yaml
- !martin:
    name: Martin D'vloper
    job: Developer
    skills:
      - python
      - perl
      - pascal
- !tabitha:
    name: Tabitha Bitumen
    job: Developer
    skills:
      - lisp
      - fortran
      - erlang
```
再修改一下执行代码
```java
List<NodeBean> list = mapper.convertValue(treeNode, new TypeReference<List<NodeBean>>() {});
//或者loop处理均可把yaml项映射成pojo
if (treeNode.isContainerNode()) {
    for (int i = 0; i < treeNode.size(); i++) {
        TreeNode node = treeNode.get(i);
        NodeBean bean = mapper.treeToValue(node, NodeBean.class);
        System.out.println(bean);
    }
}
```

<div id = "js2"></div>

- 将yaml中一个属性映射成pojo对象多个fields
```yaml
- !trace
  target_class: com.example.jackson.yaml.Processor
  target_method: +    <-由于解析中不允许使用*作为属性值，只能用+替换表示
```
通过@JsonCreator标签设定构造bean的过程来控制映射规则
```java
public class AspectConvert {
    public static void main(String[] args) throws IOException {
        YAMLFactory yamlFactory = new YAMLFactory();
        ObjectMapper mapper = new ObjectMapper();
        YAMLParser parser = yamlFactory.createParser(new File("api.yaml"));
        TreeNode treeNode = mapper.readTree(parser);

        if (treeNode.isContainerNode()) {
            for (int i = 0; i < treeNode.size(); i++) {
                TreeNode node = treeNode.get(i);
                ConfigBean bean = mapper.treeToValue(node, ConfigBean.class);
                System.out.println(bean);
                STGroupFile group = new STGroupFile("./trace.stg");
                ST controllerClass = group.getInstanceOf("TraceAspectClass");
                ST beanClass = group.getInstanceOf("TraceBeanClass");
                controllerClass.add("c", bean);
                beanClass.add("bc", bean);
                System.out.println(controllerClass.render());
                System.out.println(beanClass.render());
            }
        }
    }

    public static class ConfigBean {
        private String name;
        private String packageName;
        private String method;

        @JsonCreator
        public ConfigBean(@JsonProperty("target_class") String target,
                          @JsonProperty("target_method") String method) {
            //将target_class属性拆分成 包名和类名
            int index = target.lastIndexOf('.');
            if (index != -1) {
                this.name = target.substring(++index);
                this.packageName = target.substring(0, --index);
            } else {
                this.name = target;
                this.packageName = target;
            }
            // 将 * 符号替换回
            this.method = method.replace('+', '*');
        }

        public String getName() {
            return name;
        }

        public String getPackageName() {
            return packageName;
        }

        public String getMethod() {
            return method;
        }

        @Override
        public String toString() {
            return "ConfigBean{" +
                    "name='" + name + '\'' +
                    ", packageName='" + packageName + '\'' +
                    ", method='" + method + '\'' +
                    '}';
        }
    }
}
```
执行结果
```
ConfigBean{name='Processor', packageName='com.example.jackson.yaml', method='*'}
```
如果target_method字段即要支持单string类型，又能支持string list类型
```yaml
# string类型
- !trace
  target_class: com.example.jackson.yaml.Processor
  target_method: method1

# string list类型
- !trace
  target_class: com.example.jackson.yaml.Processor
  target_method: 
    - method1
    - method2  
```
以上程序执行就会报错
```console
Exception in thread "main" com.fasterxml.jackson.databind.exc.MismatchedInputException: Cannot deserialize instance of `java.util.ArrayList` out of VALUE_STRING token
```
解决办法就是修改creator中类型,增加@JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
```java
public class AspectConvert {
    ...

    public static class ConfigBean {
        private String name;
        private String packageName;
        private List<String> methods;

        @JsonCreator
        public ConfigBean(@JsonProperty("target_class") String target,
                          @JsonProperty("target_method") 
                          @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY) 
                          List<String> targetMethods) {
            //将target_class属性拆分成 包名和类名
            int index = target.lastIndexOf('.');
            if (index != -1) {
                this.name = target.substring(++index);
                this.packageName = target.substring(0, --index);
            } else {
                this.name = target;
                this.packageName = target;
            }
            // 将 * 符号替换回
            if (targetMethods != null && !targetMethods.isEmpty()) {
                this.methods = targetMethods.stream().map(
                        methodName -> {return methodName.replace('+', '*');})
                        .collect(Collectors.toList());
            }
        }
        ...

        public String getMethods() {
            return methods;
        }

        @Override
        public String toString() {
            return "ConfigBean{" +
                    "name='" + name + '\'' +
                    ", packageName='" + packageName + '\'' +
                    ", method='" + methods + '\'' +
                    '}';
        }
    }
}
```
执行结果
```
ConfigBean{name='Processor', packageName='com.example.jackson.yaml', methods=[method1, method2]'}
```

<div id = "js2st"></div>

- 结合ST将yaml数据导出生成文件

ST是[StringTemplate](https://github.com/antlr/stringtemplate4/blob/master/doc/introduction.md)缩写，antlr下面的子项目实现Template Engines功能，按照[template语法](https://github.com/antlr/stringtemplate4/blob/master/doc/templates.md)写的st/stg文件能够根据输入产生出文件。上面一个例子就是通过解析yaml文件得到配置的值，然后render到template中生成不同内容文件。

一个简单的stg文件如下,包含多个st如Copyright()， TraceBeanClass(bc)

默认变量声明开始符'<', 结束符'>',也可以构造stg指定其他符号如'$'
```
Copyright() ::= <<
/* *********************************************************************
 * Copyright Myself.  All rights reserved.
 * *********************************************************************/

/* *************************************************
 * THIS FILE IS AUTO-GENERATED, DO NOT MODIFY.
 * *************************************************/
>>

TraceBeanClass(bc) ::= <<
<Copyright()>
package <bc.packageName>.trace;

import org.aspectj.lang.JoinPoint;

public interface <bc.name>Aji {
    default void startTrace(JoinPoint joinPoint) {
    }

    default void endTrace(JoinPoint joinPoint) {
    }
}
>>
```
上面例子中解析yaml文件产生config bean，然后通过bean属性来render模版
```java
STGroupFile group = new STGroupFile("./trace.stg");
ST beanClass = group.getInstanceOf("TraceBeanClass");
beanClass.add("bc", bean);
System.out.println(beanClass.render());
```
产生文件输出
```java
/* *********************************************************************
 * Copyright Myself.  All rights reserved.
 * *********************************************************************/

/* *************************************************
 * THIS FILE IS AUTO-GENERATED, DO NOT MODIFY.
 * *************************************************/
package com.example.jackson.yaml.trace;

import org.aspectj.lang.JoinPoint;

public interface ProcessorAji {
    default void startTrace(JoinPoint joinPoint) {
    }

    default void endTrace(JoinPoint joinPoint) {
    }
}
```
stg模版使用有一些注意点   
+ 保留关键字
```java
@Component
public class <c.name>AjImpl implements <c.name>AjIf {
    private static Logger logger = Logger.getLogger(LogMessageId.NONE);
    // <>是stringtemplate系统变量标识，因此代码模版中出现时候避免识别成变量
    // 需要用 \ 做转义
    private HashMap\<String, Span> spanMap = new HashMap\<>();
    ...
}
```
+ 循环模版处理
```java
pc(index) ::= <<
pcd<index>()
>>

PCD(method, index) ::= <<
@Pointcut("execution(* <c.packageName>.<c.name>.<method>(..))")
public void <pc(index)> {
}

TraceAspectClass(c) ::= <<
...
    @Autowired
    <c.name>AjIf ajiBean;

    // 循环需要传入list变量
    // i0标识counter从0开始
    // i标识counter从1开始
    // separator指定生成段之间分割
    <c.methods:{method | <PCD(method, i0)>};separator="\n\n">

    @Pointcut("<c.methods:{method | <pc(i0)>};separator=" || ">")
    public void tracePointCut() {
    }
>>
```
可以生成java代码
```java
...
    @Pointcut("execution(* com.example.method1(..))")
    public void pcd0() {
    }

    @Pointcut("execution(* com.example.method2(..))")
    public void pcd1() {
    }

    @Pointcut("pcd0() || pcd1()")
    public void tracePointCut() {
    }
...    
```

