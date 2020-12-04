package king.law.yaml;

import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLParser;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class YamlConvert {

    public static void main(String[] args) throws IOException {
        YAMLFactory yamlFactory = new YAMLFactory();
        ObjectMapper mapper = new ObjectMapper();
        YAMLParser parser = yamlFactory.createParser(new File("person.yaml"));
        TreeNode treeNode = mapper.readTree(parser);
        List<NodeBean> list = mapper.convertValue(treeNode, new TypeReference<List<NodeBean>>() {
        });

        if (treeNode.isContainerNode()) {
            for (int i = 0; i < treeNode.size(); i++) {
                TreeNode node = treeNode.get(i);
                NodeBean bean = mapper.treeToValue(node, NodeBean.class);
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
