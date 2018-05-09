***

## Ways to json processing

### 1.use com.fasterxml.jackson
- 由 String 构造 JsonNode
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
- 创建 JsonNode 导出 String 格式
```java
ObjectMapper mapper = new ObjectMapper();
ObjectNode rootNode = mapper.createObjectNode();
NodeAssignment firstNode = nodeAssignments.get(0);
rootNode.put(AUTHOR, firstNode.getAuthor().toString());
rootNode.put(TIMESTAMP, firstNode.getTimestamp());
ArrayNode sliceNode = rootNode.putArray(SLICE);
for (NodeAssignment node : nodeAssignments) {
    logger.info("#cluster#Leader[{}] dispenses sharding data for node[{}]\n{}", voter.getName(), node.getUuid(),
            node);
    ObjectNode slice = mapper.createObjectNode();
    slice.put(NODE_UUID, node.getUuid().toString());
    slice.put(NODE_IP, nodeMap.get(node.getUuid()));
    slice.put(SLICE_COUNT, node.getAssignment().cardinality());
    slice.put(OCCUPATION, BinaryNode.valueOf(node.getAssignment().toByteArray()).asText());
    sliceNode.add(slice);
}
String jsonString = null;
try {
    jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(rootNode);
    logger.info("#cluster#Leader[{}] tries to save latest sharding data into zk \n{}", voter.getName(),
            jsonString);
    cf.setData().forPath(CLUSTER_SHARDING, jsonString.getBytes());
} catch (Exception e) {
    logger.error("Error while saving sharding data due to: {}", e);
}
```
