package gene.monitor.web;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jdi.VirtualMachine;

import java.io.IOException;

import gene.monitor.jdi.JdiClient;
import gene.monitor.jdi.VarMonitor;
import gene.monitor.multithread.MultiThreadProgram;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MeasureController {
    @RequestMapping("/index")
    public String index() {
        return "Greetings from Spring Boot!";
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
