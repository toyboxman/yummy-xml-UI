package king.law.application.cli;

import io.vertx.core.cli.CLI;
import io.vertx.core.cli.annotations.Argument;
import io.vertx.core.cli.annotations.CLIConfigurator;
import io.vertx.core.cli.annotations.Description;
import io.vertx.core.cli.annotations.Name;
import io.vertx.core.cli.annotations.Option;
import io.vertx.core.cli.annotations.Summary;

@Name("trace-client")
@Summary("Trace Client")
@Description("EXAMPLES:\n" + "  java -jar client.jar 127.0.0.1 3658\n"
        + "  java -jar client.jar -c 'dashboard -n 1' \n"
        + "  java -jar client.jar -f batch.as 127.0.0.1\n")
public class TraceCLI {
    private boolean help = false;

    private String targetIp = "127.0.0.1";
    private int port = 3658;

    private String command;

    @Argument(argName = "target-ip", index = 0, required = false)
    @Description("Target ip")
    public void setTargetIp(String targetIp) {
        this.targetIp = targetIp;
    }

    @Argument(argName = "port", index = 1, required = false)
    @Description("The remote server port")
    public void setPort(int port) {
        this.port = port;
    }

    @Option(longName = "help", flag = true)
    @Description("Print usage")
    public void setHelp(boolean help) {
        this.help = help;
    }

    @Option(shortName = "c", longName = "command")
    @Description("Command to execute, multiple commands separated by ;")
    public void setCommand(String command) {
        this.command = command;
    }

    public String prompt() {
        CLI cli = CLIConfigurator.define(TraceCLI.class);
        return usage(cli);
    }

    private static String usage(CLI cli) {
        StringBuilder usageStringBuilder = new StringBuilder();
        cli.usage(usageStringBuilder);
        return usageStringBuilder.toString();
    }
}
