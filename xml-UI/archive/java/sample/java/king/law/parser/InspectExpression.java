package king.law.parser;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseResult;
import com.github.javaparser.ParseStart;
import com.github.javaparser.ParserConfiguration;
import com.github.javaparser.StringProvider;

public class InspectExpression {
    public static void main(String[] args) {
        ParserConfiguration configuration = new ParserConfiguration();
        JavaParser parser = new JavaParser(configuration);
        ParseResult parseResult = parser.parse(ParseStart.EXPRESSION, new StringProvider("1+1"));
        parseResult.ifSuccessful(exp -> {
            System.out.println(parseResult.getProblems().toString());
        });
        if (!parseResult.isSuccessful()) {
            System.out.println(parseResult.getProblems().toString());
        }
        // a failed parse does not always mean there is no result.
        parseResult.getResult().ifPresent(System.out::println);
        if (parseResult.getCommentsCollection().isPresent()) {
            // ...
        }
    }
}
