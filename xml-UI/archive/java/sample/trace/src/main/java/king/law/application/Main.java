package king.law.application;

import king.law.application.banner.Banner;
import king.law.application.cli.TraceCLI;

public class Main {
    public static void main(String[] args) {
        String banner = new Banner().getBanner();
        System.out.println(banner);

        String prompt = new TraceCLI().prompt();
        System.out.println(prompt);

        System.out.println("Hello world!");
    }
}