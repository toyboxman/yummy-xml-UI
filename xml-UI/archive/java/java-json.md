***

## Ways to json processing

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
