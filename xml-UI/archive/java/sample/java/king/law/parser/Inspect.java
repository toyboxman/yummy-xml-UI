package king.law.parser;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.printer.XmlPrinter;
import com.github.javaparser.printer.YamlPrinter;

public class Inspect {
    public static void main(String[] args) {
        // Parse the code you want to inspect:
        CompilationUnit cu = StaticJavaParser.parse("class X { int x; }");
        // Now comes the inspection code:
        System.out.println(cu);
        cu.addImport("com.github.javaparser.*");
        NodeList<ImportDeclaration> imports = cu.getImports();
        imports.forEach(System.out::println);
        System.out.println(cu);

        // Now comes the inspection code:
        YamlPrinter printer = new YamlPrinter(true);
        System.out.println(printer.output(cu));

        // Now comes the inspection code:
        XmlPrinter xmlprinter = new XmlPrinter(true);
        System.out.println(xmlprinter.output(cu));
    }
}