package king.law.yaml;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLParser;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroupFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.stream.Collectors;

public class AspectConvert {
    public static void main(String[] args) throws IOException {
        YAMLFactory yamlFactory = new YAMLFactory();
        ObjectMapper mapper = new ObjectMapper();
        YAMLParser parser = yamlFactory.createParser(new File("api.yaml"));
        TreeNode treeNode = mapper.readTree(parser);

        if (treeNode.isContainerNode()) {
            for (int i = 0; i < treeNode.size(); i++) {
                TreeNode node = treeNode.get(i);
                TraceConfigBean bean = mapper.treeToValue(node, TraceConfigBean.class);
                System.out.println(bean);
                STGroupFile group = new STGroupFile("./trace.stg");
                ST controllerClass = group.getInstanceOf("TraceAspectClass");
                ST beanClass = group.getInstanceOf("TraceBeanClass");
                ST beanImpl = group.getInstanceOf("TraceBeanImpl");
                controllerClass.add("c", bean);
                beanClass.add("c", bean);
                beanImpl.add("c", bean);
//                System.out.println(controllerClass.render());
//                System.out.println(beanClass.render());
                String file_separator = System.getProperty("file.separator");
                String path = bean.getCodePath().replace(".", file_separator);
                Path dir = Paths.get("./", path);
                System.out.println(dir);
                Files.createDirectories(dir);

                Path ifPath = dir.resolve(bean.getName() + "AjIf.java");
                if (!Files.exists(ifPath)) {
                    Files.createFile(ifPath);
                }
                Files.write(ifPath, beanClass.render().getBytes(), StandardOpenOption.TRUNCATE_EXISTING);

                Path classPath = dir.resolve(bean.getName() + "TraceAspect.java");
                if (!Files.exists(classPath)) {
                    Files.createFile(classPath);
                }
                Files.write(classPath, controllerClass.render().getBytes(), StandardOpenOption.TRUNCATE_EXISTING);

                Path implPath = dir.resolve(bean.getName() + "AjImpl.java");
                if (!Files.exists(implPath)) {
                    Files.createFile(implPath);
                }
                Files.write(implPath, beanImpl.render().getBytes(), StandardOpenOption.TRUNCATE_EXISTING);
            }
        }
    }

    public static class TraceConfigBean {
        private String name;
        private String packageName;
        private List<String> methods;
        private String codePath;
        private String serviceName;

        @JsonCreator
        public TraceConfigBean(@JsonProperty("target_class") String target,
                               @JsonProperty("code_package") String codePackage,
                               @JsonProperty("target_service") String service,
                               @JsonProperty("target_method")
                               @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
                                       List<String> targetMethods) {
            this.serviceName = "NsxTraceType." + (service == null ? "policy" : service);
            int index = target.lastIndexOf('.');
            if (index != -1) {
                this.name = target.substring(++index);
                this.packageName = target.substring(0, --index);
                if (codePackage != null) {
                    this.codePath = codePackage;
                } else {
                    this.codePath = this.packageName;
                }
            } else {
                this.name = target;
                this.packageName = target;
                this.codePath = codePackage;
            }
            if (targetMethods != null && !targetMethods.isEmpty()) {
                this.methods = targetMethods.stream().map(
                        methodName -> {
                            return methodName.replace('+', '*');
                        })
                        .collect(Collectors.toList());
            }
        }

        public String getName() {
            return name;
        }

        public String getPackageName() {
            return packageName;
        }

        public List<String> getMethods() {
            return methods;
        }

        public String getCodePath() {
            return codePath;
        }

        public String getServiceName() {
            return serviceName;
        }

        @Override
        public String toString() {
            return "TraceConfigBean{" +
                    "name='" + name + '\'' +
                    ", packageName='" + packageName + '\'' +
                    ", methods=" + methods +
                    ", codePath='" + codePath + '\'' +
                    ", serviceName='" + serviceName + '\'' +
                    '}';
        }
    }
}
