package gene.monitor.web;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jdi.VirtualMachine;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import gene.monitor.jdi.JdiClient;
import gene.monitor.jdi.VarMonitor;
import gene.monitor.multithread.MultiThreadProgram;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MeasureController {
    public static final int COLUMN_LEN = 25;
    private Map<String, String> cache = new HashMap<>();
    private Map<String, VirtualMachine> connectionPool = new HashMap<>();

    @RequestMapping("/index")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @PostMapping("/action")
    @ResponseBody
    public ResponseEntity scheduleAction(@RequestBody String action) {
        System.out.println(action);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode configNode = null;
        try {
            configNode = mapper.readTree(action);
        } catch (IOException e) {
            e.printStackTrace();
            ResponseEntity<String> entity = new ResponseEntity<>("Invalid post content",
                HttpStatus.BAD_REQUEST);
            return entity;
        }

        String host = configNode.get("target").get("ip").asText();
        int port = configNode.get("target").get("port").asInt();
        VirtualMachine machine = null;
        if (!connectionPool.containsKey(host + ":" + port)) {
            JdiClient client = new JdiClient();
            try {
                machine = client.connect(host, port);
                connectionPool.put((host + ":" + port), machine);
            } catch (IOException e) {
                e.printStackTrace();
                ResponseEntity<String> entity = new ResponseEntity<>("Target vm is not found",
                    HttpStatus.NOT_FOUND);
                return entity;
            }
        } else {
            machine = connectionPool.get(host + ":" + port);
        }

        String class_name = configNode.get("class_name").asText();
        String var_name = configNode.get("obj_name").asText();
        int location = configNode.get("location").asInt();
        if (!cache.containsKey(host + ":" + port + ":" + location)) {
            VarMonitor monitor = new VarMonitor(0, machine);
            monitor.monitorVar(class_name, var_name, location, cache);
            cache.put((host + ":" + port + ":" + location), "");
        }
        ResponseEntity<String> entity = new ResponseEntity<>("Successfully schedule an action", HttpStatus.CREATED);
        return entity;
    }

    @RequestMapping(value = "/monitor/var/{name}", method = RequestMethod.GET)
    @ResponseBody
    public String queryValue(@PathVariable("name") String name) {
        if (!cache.containsKey(name)) {
            cache.put("test", "9000");
            return "No result for query";
        }
        String header = "        Var Name         |           Value         ";
        String line = "-------------------------+-------------------------";
        buildRecord(name);
        StringBuilder builder = new StringBuilder();
        builder.append(header)
            .append('\n')
            .append(line)
            .append('\n')
            .append(buildRecord(name))
            .append('+')
            .append(buildRecord(cache.get(name)));
        return builder.toString();
    }

    private StringBuilder buildRecord(String val) {
        int head_len = COLUMN_LEN - val.length() > 0 ? (COLUMN_LEN - val.length()) / 2 : 0;
        int tail_len = COLUMN_LEN - head_len - val.length() > 0 ? COLUMN_LEN - head_len - val.length() : 0;
        StringBuilder record = new StringBuilder();
        for (int i = 0; i < head_len; i++) {
            record.append(' ');
        }
        record.append(val);
        for (int i = 0; i < tail_len; i++) {
            record.append(' ');
        }
        return record;
    }

    @PostMapping("/threads")
    public String scheduleThreads(@RequestBody String threads) throws IOException {
        System.out.println(threads);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode configNode = mapper.readTree(threads);
        String host = configNode.get("target").get("ip").asText();
        int port = configNode.get("target").get("port").asInt();
        JdiClient client = new JdiClient();
        VirtualMachine machine = client.connect(host, port);
        VarMonitor monitor = new VarMonitor(19, machine);
        monitor.enableMonitor(MultiThreadProgram.class.getName());
        return "enables threads scheduling";
    }
}
