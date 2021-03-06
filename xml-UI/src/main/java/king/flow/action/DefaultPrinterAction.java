/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.flow.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.charset.Charset;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.SwingWorker;
import static king.flow.common.CommonUtil.cleanPrintMsg;
import static king.flow.common.CommonUtil.getResourceMsg;
import static king.flow.common.CommonUtil.printReceipt;
import static king.flow.common.CommonUtil.retrievePrintMsg;
import static king.flow.common.CommonUtil.showErrorMsg;
import static king.flow.common.CommonUtil.showMsg;
import king.flow.view.Action.SetPrinterAction;

/**
 *
 * @author Administrator
 */
public class DefaultPrinterAction extends DefaultBaseAction {

    private final String header;
    private final String tail;
    private final SetPrinterAction.Debug debugMode;
    static Charset GBK = Charset.forName("GBK");
    static Charset UTF8 = Charset.forName("UTF8");

    public DefaultPrinterAction(String header, String tail) {
        this.header = header;
        this.tail = tail;
        this.debugMode = null;
    }

    public DefaultPrinterAction(String header, String tail, SetPrinterAction.Debug debugMode) {
        this.header = header;
        this.tail = tail;
        this.debugMode = debugMode;
    }

    @Override
    protected void installButtonAction() {
        JButton btn = (JButton) owner;
        btn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                waitCommunicationTask(new PrinterWorker());
            }
        });
    }

    private class PrinterWorker extends SwingWorker<String, Integer> {

        @Override
        protected String doInBackground() throws Exception {
            Thread.sleep(TimeUnit.SECONDS.toMillis(1));
            String content = retrievePrintMsg();
            if (content == null || content.length() == 0) {
                showMsg(owner.getTopLevelAncestor(), getResourceMsg("printer.done.prompt"));
            } else {
                //printer cannot recognize UTF8 , change to GBK
//                content = new String(content.getBytes(UTF8), GBK);
//                String header_ex = new String(header.getBytes(UTF8), GBK);
//                String tail_ex = new String(tail.getBytes(UTF8), GBK);
//                printReceipt(header_ex, content, tail_ex);
                if (debugMode != null) {
                    Thread.sleep(TimeUnit.SECONDS.toMillis(2));
                    showMsg(owner.getTopLevelAncestor(), debugMode.getPrompt());
                    cleanPrintMsg();
                } else {
                    printReceipt(header, content, tail);
                    cleanPrintMsg();
                }
            }

            return null;
        }

        @Override
        protected void done() {
            try {
                get();
            } catch (InterruptedException | ExecutionException ex) {
                Logger.getLogger(DefaultPrinterAction.class.getName()).log(Level.SEVERE, "Printer is out of service", ex);
                showErrorMsg(owner.getTopLevelAncestor(), getResourceMsg("printer.status.error.prompt"));
            }
        }

    }
}
