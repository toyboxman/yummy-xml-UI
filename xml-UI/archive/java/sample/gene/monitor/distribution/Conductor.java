package gene.monitor.distribution;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Conductor {
    static class ConsoleOutputStream extends PrintStream {

        public ConsoleOutputStream(OutputStream out) {
            super(out);
        }

        @Override
        public void write(byte[] buf, int off, int len) {
            String output = new String(buf);
            // disable spring template log
            if (output.contains("springframework")) return;
            super.write(buf, off, len);
        }
    }

    public static void main(String... args) {
        System.setOut(new ConsoleOutputStream(System.out));
        System.out.println("**************************************************");
        System.out.println("Distributed Monitor Conductor");
        System.out.println("**************************************************");
        System.out.println();
        Complier complier = new Complier();
        while (true) {
            System.out.print("MOQL> ");

            Scanner scanner = new Scanner(System.in);
            String moql_statement = scanner.nextLine();
            if (moql_statement.equals("\\q")) {
                System.exit(0);
            }
            try {
                complier.compile(moql_statement);
            } catch (Exception e) {
            }
        }
    }
}
