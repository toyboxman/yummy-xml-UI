***

## Ways to json processing
[Jackson Project](https://github.com/FasterXML/jackson)��һ�����е�JSON library for Java, ������JSON����parser/generator, POJO������JSON���ݻ���ת�����Լ��������ݸ�ʽ������Avro, BSON, CBOR, CSV, Smile, (Java) Properties, Protobuf, XML or YAML
+ [jackson-annotationsʹ�ý���](https://github.com/FasterXML/jackson-annotations)
+ [jackson-databindʹ�ý���](https://github.com/FasterXML/jackson-databind)
+ [jackson-coreʹ�ý���](https://github.com/FasterXML/jackson-core)
+ [mapping��̬�仯json����](https://www.baeldung.com/jackson-mapping-dynamic-object)
+ [json���ݶ��ֵmapping��һ������field](https://www.baeldung.com/json-multiple-fields-single-java-field)
+ [jsonһ��ֵmapping��һ��������field](#js2)
+ [jackson tree node�ṹʹ�ý���](https://www.baeldung.com/jackson-json-node-tree-model)
+ [���ST��yaml���ݵ��������ļ�](#js2st)

### 1.use com.fasterxml.jackson
- ��String����JsonNode
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
- ����JsonNode����String
```java
ObjectMapper mapper = new ObjectMapper();
//����json�ĸ��ڵ�
ObjectNode rootNode = mapper.createObjectNode();
rootNode.put(AUTHOR, getAuthor().toString());
rootNode.put(TIMESTAMP, getTimestamp());
//���ڵ��´���һ���������ӽڵ�
ArrayNode sliceNode = rootNode.putArray(SLICE);
for (Assignment node : AssignmentList) {
    ObjectNode slice = mapper.createObjectNode();
    slice.put(NODE_UUID, node.getUuid().toString());
    slice.put(NODE_IP, nodeMap.get(node.getUuid()));
    slice.put(SLICE_COUNT, node.getCount());
    //��bytes���ݴ������BASE64��ʽString,��ֹͨ�����紫����ִ���
    slice.put(OCCUPATION, BinaryNode.valueOf(node.getAssignment().toByteArray()).asText());
    sliceNode.add(slice);
}
String jsonString = null;
try {
    jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(rootNode);
} catch (Exception e) {
}
```
- ��json Stringת����pojo����
```java
ObjectMapper mapper = new ObjectMapper();
try {
    TypeFactory typeFactory = mapper.getTypeFactory();
    //���ռ������Ͷ���pojo list
    CollectionType collectionType = typeFactory.constructCollectionType(List.class, TracerPojo.class);
    List<TracerPojo> list = mapper.readValue(vertical_configs.getValue().toString(), collectionType);
    //���ռ������Ͷ���pojo list
    TypeReference<List<TracerPojo>> typeReference = new TypeReference<List<TracerPojo>>() {};
    list = mapper.readValue(json, typeReference);
} catch (IOException e) {
    e.printStackTrace();
}
```
- ��yaml����ת����pojo����
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
        //ֱ��ת���ɼ������ݣ��õ�������map��list
        //martin -> {LinkedHashMap@995}  size = 3
            //name -> Martin D'vloper, ...
        //tabitha -> {LinkedHashMap@1000}  size = 3
        //����NodeBeanû��ֱ�ӿ���ӳ�䵽 martin ->�� ����޷�ֱ�ӵõ�List<NodeBean>
        List list = mapper.treeToValue(treeNode, List.class);

        if (treeNode.isContainerNode()) {
            for (int i = 0; i < treeNode.size(); i++) {
                TreeNode node = treeNode.get(i);
                // node.fieldNames().next()�õ� martin
                // node.get(node.fieldNames().next())�õ�value
                // {"name":"Martin D'vloper","job":"Developer","skills":["python","perl","pascal"]}
                // �����json�����ܱ�ӳ���NodeBean
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
ִ�н��
```
NodeBean{job='Developer', name='Martin D'vloper', skills=[python, perl, pascal]}
NodeBean{job='Developer', name='Tabitha Bitumen', skills=[lisp, fortran, erlang]}
```
�����yaml�ļ��޸�һ�£�ע��������key !martin�� !tabitha
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
���޸�һ��ִ�д���
```java
List<NodeBean> list = mapper.convertValue(treeNode, new TypeReference<List<NodeBean>>() {});
//����loop������ɰ�yaml��ӳ���pojo
if (treeNode.isContainerNode()) {
    for (int i = 0; i < treeNode.size(); i++) {
        TreeNode node = treeNode.get(i);
        NodeBean bean = mapper.treeToValue(node, NodeBean.class);
        System.out.println(bean);
    }
}
```

<div id = "js2"></div>

- ��yaml��һ������ӳ���pojo������fields
```yaml
- !trace
  target_class: com.example.jackson.yaml.Processor
  target_method: +    <-���ڽ����в�����ʹ��*��Ϊ����ֵ��ֻ����+�滻��ʾ
```
ͨ��@JsonCreator��ǩ�趨����bean�Ĺ���������ӳ�����
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
            //��target_class���Բ�ֳ� ����������
            int index = target.lastIndexOf('.');
            if (index != -1) {
                this.name = target.substring(++index);
                this.packageName = target.substring(0, --index);
            } else {
                this.name = target;
                this.packageName = target;
            }
            // �� * �����滻��
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
ִ�н��
```
ConfigBean{name='Processor', packageName='com.example.jackson.yaml', method='*'}
```
���target_method�ֶμ�Ҫ֧�ֵ�string���ͣ�����֧��string list����
```yaml
# string����
- !trace
  target_class: com.example.jackson.yaml.Processor
  target_method: method1

# string list����
- !trace
  target_class: com.example.jackson.yaml.Processor
  target_method: 
    - method1
    - method2  
```
���ϳ���ִ�оͻᱨ��
```console
Exception in thread "main" com.fasterxml.jackson.databind.exc.MismatchedInputException: Cannot deserialize instance of `java.util.ArrayList` out of VALUE_STRING token
```
����취�����޸�creator������,����@JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
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
            //��target_class���Բ�ֳ� ����������
            int index = target.lastIndexOf('.');
            if (index != -1) {
                this.name = target.substring(++index);
                this.packageName = target.substring(0, --index);
            } else {
                this.name = target;
                this.packageName = target;
            }
            // �� * �����滻��
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
ִ�н��
```
ConfigBean{name='Processor', packageName='com.example.jackson.yaml', methods=[method1, method2]'}
```

<div id = "js2st"></div>

- ���ST��yaml���ݵ��������ļ�

ST��[StringTemplate](https://github.com/antlr/stringtemplate4/blob/master/doc/introduction.md)��д��antlr���������Ŀʵ��Template Engines���ܣ�����[template�﷨](https://github.com/antlr/stringtemplate4/blob/master/doc/templates.md)д��st/stg�ļ��ܹ���������������ļ�������һ�����Ӿ���ͨ������yaml�ļ��õ����õ�ֵ��Ȼ��render��template�����ɲ�ͬ�����ļ���

һ���򵥵�stg�ļ�����,�������st��Copyright()�� TraceBeanClass(bc)

Ĭ�ϱ���������ʼ��'<', ������'>',Ҳ���Թ���stgָ������������'$'
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
���������н���yaml�ļ�����config bean��Ȼ��ͨ��bean������renderģ��
```java
STGroupFile group = new STGroupFile("./trace.stg");
ST beanClass = group.getInstanceOf("TraceBeanClass");
beanClass.add("bc", bean);
System.out.println(beanClass.render());
```
�����ļ����
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
stgģ��ʹ����һЩע���   
+ �����ؼ���
```java
@Component
public class <c.name>AjImpl implements <c.name>AjIf {
    private static Logger logger = Logger.getLogger(LogMessageId.NONE);
    // <>��stringtemplateϵͳ������ʶ����˴���ģ���г���ʱ�����ʶ��ɱ���
    // ��Ҫ�� \ ��ת��
    private HashMap\<String, Span> spanMap = new HashMap\<>();
    ...
}
```
+ ѭ��ģ�洦��
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

    // ѭ����Ҫ����list����
    // i0��ʶcounter��0��ʼ
    // i��ʶcounter��1��ʼ
    // separatorָ�����ɶ�֮��ָ�
    <c.methods:{method | <PCD(method, i0)>};separator="\n\n">

    @Pointcut("<c.methods:{method | <pc(i0)>};separator=" || ">")
    public void tracePointCut() {
    }
>>
```
��������java����
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

