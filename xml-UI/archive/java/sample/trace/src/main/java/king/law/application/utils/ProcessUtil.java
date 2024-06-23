package king.law.application.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ProcessUtil {

    public static void runCommand(String command) {
        String[] cmd = command.split(" ");
        ProcessBuilder pb = new ProcessBuilder(cmd);
        try {
            Process process = pb.start();
            OutputStream outputStream = process.getOutputStream();
            InputStream inputStream = process.getInputStream();
            copy(inputStream, System.out);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void copy(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int len;
        while ((len = in.read(buffer)) != -1) {
            out.write(buffer, 0, len);
        }
    }
}
