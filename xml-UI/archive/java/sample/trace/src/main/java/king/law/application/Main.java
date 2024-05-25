package king.law.application;

import king.law.application.banner.Banner;
import king.law.application.cli.TraceCLI;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        Path path = Paths.get("/","Users", "liujin", "source", "github", "griffin");
        Path flag = path.resolve("dq.json");
        System.out.println(Files.exists(flag));
        String banner = new Banner().getBanner();
        System.out.println(banner);

        TraceCLI traceCLI = new TraceCLI();
        String prompt = traceCLI.prompt();
//        System.out.println(prompt);

        traceCLI.runCli("--help");

        System.out.println("Hello world!");
    }
}