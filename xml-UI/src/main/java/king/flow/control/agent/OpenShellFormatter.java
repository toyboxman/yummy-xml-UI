/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.flow.control.agent;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.LogRecord;
import java.util.logging.SimpleFormatter;

/**
 *
 * @author liujin
 */
public class OpenShellFormatter extends SimpleFormatter {

    private static final String FORMAT = "%s%n%s";

    @Override
    public synchronized String format(LogRecord record) {
        String message = formatMessage(record);
        String throwable = "";
        if (record.getThrown() != null) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            pw.println();
            record.getThrown().printStackTrace(pw);
            pw.close();
            throwable = sw.toString();
        }
        return String.format(FORMAT, message, throwable);
    }

}
