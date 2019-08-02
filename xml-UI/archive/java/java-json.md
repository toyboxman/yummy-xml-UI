***

## Ways to json processing

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
