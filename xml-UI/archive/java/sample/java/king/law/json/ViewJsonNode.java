package king.law.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.*;
import java.util.stream.StreamSupport;

public class ViewJsonNode {

    private static ObjectMapper mapper = new ObjectMapper();

    /**
     * @param args https://www.baeldung.com/java-jsonnode-get-keys
     *             https://www.baeldung.com/jackson-json-node-tree-model
     */
    public static void main(String[] args) throws IOException {
        viewJsonNodeTree();
    }

    static String jsonTree = "[\n" +
            "    {\n" +
            "        \"mac_address\": \"00:50:56:b9:0d:6f\",\n" +
            "        \"ip\": {\n" +
            "            \"ip_addresses\": [\n" +
            "                {\n" +
            "                    \"ip_address\": \"10.185.103.89\",\n" +
            "                    \"prefix_length\": 20,\n" +
            "                    \"state\": \"PREFERRED\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"ip_address\": \"2620:124:6020:c005:250:56ff:feb9:d6f\",\n" +
            "                    \"prefix_length\": 64,\n" +
            "                    \"state\": \"UNKNOWN\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"ip_address\": \"fe80::250:56ff:feb9:d6f\",\n" +
            "                    \"prefix_length\": 64,\n" +
            "                    \"state\": \"UNKNOWN\"\n" +
            "                }\n" +
            "            ]\n" +
            "        },\n" +
            "        \"nic\": \"4000\"\n" +
            "    }\n" +
            "]";

    private static void viewJsonNodeTree() throws IOException {
        List<String> keys = new ArrayList<>();
//        JsonFactory factory = new JsonFactory();
//        JsonParser jsonParser = factory.createParser(json);
        JsonNode jsonNode = mapper.readTree(jsonTree);
        JsonParser jsonParser = jsonNode.traverse();
        while (!jsonParser.isClosed()) {
            if (jsonParser.nextToken() == JsonToken.FIELD_NAME) {
                keys.add((jsonParser.getCurrentName()));
            }
        }
        System.out.println(keys);

        if (jsonNode.isArray()) {
            JsonNode node = jsonNode.get(0);
            System.out.println();
            JsonNode ipList = node.path("ip").path("ip_addresses");
            System.out.println(ipList);
            System.out.println();
            System.out.println(ipList.path(0));
            System.out.println();
            if (ipList.isArray()) {
                ipList.elements().forEachRemaining(System.out::println);
                System.out.println();
                StreamSupport.stream(
                                Spliterators.spliteratorUnknownSize(
                                        ipList.elements(),
                                        Spliterator.ORDERED), false)
                        .filter(cnode -> cnode.path("state").asText().equals("PREFERRED"))
                        .forEach(fnode -> {
                            System.out.println("filter the iterator by state field...");
                            System.out.println(fnode.get("ip_address"));
                        });
            }
        }
    }
}
