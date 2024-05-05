package king.law.application;

import king.law.application.banner.Banner;
import king.law.application.cli.TraceCLI;

public class Main {
    public static void main(String[] args) {
        String banner = new Banner().getBanner();
        System.out.println(banner);

        TraceCLI traceCLI = new TraceCLI();
        String prompt = traceCLI.prompt();
//        System.out.println(prompt);

        traceCLI.runCli("--help");

        System.out.println("Hello world!");
    }
}