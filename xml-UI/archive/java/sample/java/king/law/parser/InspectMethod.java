package king.law.parser;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseResult;
import com.github.javaparser.ParseStart;
import com.github.javaparser.ParserConfiguration;
import com.github.javaparser.StreamProvider;
import com.github.javaparser.StringProvider;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Optional;

public class InspectMethod {
    public static void main(String[] args) throws IOException {
        ParserConfiguration configuration = new ParserConfiguration();
        JavaParser parser = new JavaParser(configuration);
        BufferedReader reader = Files.newBufferedReader(
                Paths.get("./src/main/java/king/law", "reflection", "Family.java"));
        StreamProvider provider = new StreamProvider(reader);
        ParseResult<CompilationUnit> parseResult = parser.parse(ParseStart.COMPILATION_UNIT, provider);
        parseResult.ifSuccessful(exp -> {
            System.out.println(parseResult.getResult());
            Optional<ClassOrInterfaceDeclaration> family = parseResult.getResult()
                    .get().getClassByName("Family");
            parseResult.getResult().get()
                    .getChildNodes().forEach(node -> {
                System.out.println(node.getClass().getSimpleName());
            });
            VoidVisitorAdapter<CompilationUnit> v = new VoidVisitorAdapter<CompilationUnit>() {
                @Override
                public void visit(MethodDeclaration n, CompilationUnit arg) {
                    super.visit(n, arg);
                    System.out.println(n.getModifiers());
                    System.out.println(n.getNameAsString());
                }
            };
            parseResult.getResult().get().accept(v, parseResult.getResult().get());

        });
        if (!parseResult.isSuccessful()) {
            System.out.println(parseResult.getProblems().toString());
        }
    }
}
