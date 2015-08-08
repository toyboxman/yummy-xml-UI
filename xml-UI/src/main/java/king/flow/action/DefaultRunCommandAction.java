/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.flow.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import javax.swing.JButton;
import javax.swing.SwingWorker;
import static king.flow.common.CommonUtil.getLogger;

/**
 *
 * @author Administrator
 */
public class DefaultRunCommandAction extends DefaultBaseAction {

    private String command;

    public DefaultRunCommandAction(String command) {
        this.command = command;
    }

    @Override
    protected void installButtonAction() {
        JButton btn = (JButton) owner;
        btn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                waitCommunicationTask(new RunCommandTask());
            }
        });
    }

    private class RunCommandTask extends SwingWorker<Integer, Object> {

        @Override
        protected Integer doInBackground() throws Exception {
            Process proc = Runtime.getRuntime().exec("cmd /C " + command);
            Thread.sleep(1000 * 3);
            proc.destroy();

            return 0;
        }

        @Override
        protected void done() {
            try {
                get();
            } catch (InterruptedException | ExecutionException e) {
                getLogger(RunCommandTask.class.getName()).log(Level.WARNING,
                        "Fail to run command : " + command, e);
            }
        }

    }
}
