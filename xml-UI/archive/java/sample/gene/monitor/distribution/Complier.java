package gene.monitor.distribution;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.net.URI;

import gene.moql.MoqlBaseVisitor;
import gene.moql.MoqlLexer;
import gene.moql.MoqlParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.TokenStream;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

public class Complier {
    static class MoqlVistor extends MoqlBaseVisitor<ObjectNode> {
        private ObjectNode statementNode = null;

        public MoqlVistor() {
            ObjectMapper mapper = new ObjectMapper();
            statementNode = mapper.createObjectNode();
        }

        public String toJson() {
            return statementNode.size() == 0 ? null : statementNode.toString();
        }

        @Override
        public ObjectNode visitQuery_stmt(MoqlParser.Query_stmtContext ctx) {
            statementNode.put("action", ctx.getChild(0).getText());
            return statementNode;
        }

        @Override
        public ObjectNode visitObject_name(MoqlParser.Object_nameContext ctx) {
            statementNode.put("obj_name", ctx.getChild(0).getText());
            return statementNode;
        }

        @Override
        public ObjectNode visitClass_name(MoqlParser.Class_nameContext ctx) {
            statementNode.put("class_name", ctx.getChild(0).getText());
            return statementNode;
        }

        @Override
        public ObjectNode visitTarget_vm(MoqlParser.Target_vmContext ctx) {
            JsonNode target = statementNode.get("target");
            if (target == null) {
                ObjectMapper mapper = new ObjectMapper();
                target = mapper.createObjectNode();
            }

            ((ObjectNode) target).put("ip", ctx.getText().substring(0, ctx.getText().indexOf(':')));
            ((ObjectNode) target).put("port", ctx.getStop().getText());
            statementNode.set("target", target);
            return statementNode;
        }

        @Override
        public ObjectNode visitExpr(MoqlParser.ExprContext ctx) {
            statementNode.put("location", ctx.getStop().getText());
            return statementNode;
        }
    }

    public void compile(String moql_statement) {
        final MoqlVistor visitor = new MoqlVistor();
        try {
            Lexer lexer = new MoqlLexer(CharStreams.fromString(moql_statement));
            TokenStream tokenStream = new CommonTokenStream(lexer);
            MoqlParser parser = new MoqlParser(tokenStream);
            MoqlParser.ParseContext parse = parser.parse();
            parse.accept(visitor);
            parse.moql_stmt_list().forEach(statement -> {
                statement.accept(visitor);
                MoqlParser.Query_stmtContext query_stmtContext = statement.moql_stmt().query_stmt();
                query_stmtContext.accept(visitor);
                query_stmtContext.target_vm().accept(visitor);
                query_stmtContext.object_name().forEach(obj_name -> {
                    obj_name.accept(visitor);
                });
                query_stmtContext.class_name().accept(visitor);
                query_stmtContext.expr().accept(visitor);
            });
            System.out.println(visitor.toJson());
            if (visitor.toJson() != null) {
                RestTemplate template = new RestTemplateBuilder().build();
                HttpEntity<String> request = new HttpEntity<>(visitor.toJson());
                URI location = template
                    .postForLocation("http://127.0.0.1:8080/action", request);
                int times = 10;
                while (times > 0) {
                    String value = template.getForObject("http://127.0.0.1:8080/monitor/var/"
                        + visitor.statementNode.get("obj_name").asText(), String.class);
                    if (value.equals("No result for query")) {
                        times--;
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        System.out.println(value);
                        return;
                    }
                }
                System.out.println("No result for query");
            }
        } catch (Exception e) {
            throw e;
        }
    }
}
