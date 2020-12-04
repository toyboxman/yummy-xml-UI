package king.law.json;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class JsonConvert {
    public static void main(String[] args) throws IOException {
        Set<String> s = ConcurrentHashMap.newKeySet();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(JsonConvert.class.getResource("/trace-config.json"));
        JsonNode filterList = jsonNode.get("filter_list");
        if (filterList.isArray()) {
            filterList.forEach(System.out::println);
            filterList.forEach(node -> {
                System.out.println(node.asText());
                s.add(node.asText());
            });
        }
        System.out.println(s);
        System.out.println(s.contains("name"));
        s.clear();
        System.out.println(s);
    }
}
