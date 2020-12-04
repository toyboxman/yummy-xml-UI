package king.law.antlr.template;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

public class BuildFromTemplate {
    public static void main(String[] args) {
        STGroup group = new STGroupFile("./as.stg");
        ST decl = group.getInstanceOf("decl");
        decl.add("type", "decl_type");
        decl.add("name", "decl_name");
        decl.add("val", "decl_val");
        System.out.println(decl.render());
        group = new STGroupFile("./trace.stg");
        ST controllerClass = group.getInstanceOf("TraceAspectClass");
        ST beanClass = group.getInstanceOf("TraceBeanClass");
        MetaClass metaClass = new MetaClass();
        controllerClass.add("c", metaClass);
        beanClass.add("bc", metaClass);
        System.out.println(controllerClass.render());
        System.out.println(beanClass.render());
    }

    public static class MetaClass {
        public String packageName = "king.law.antlr.template";
        private String name = "BuildFromTemplate";

        public String getName() {
            return name;
        }
    }
}
