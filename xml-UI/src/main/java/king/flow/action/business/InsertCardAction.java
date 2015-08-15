/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.flow.action.business;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.SwingWorker;
import king.flow.action.DefaultBaseAction;

/**
 *
 * @author liujin
 */
public class InsertCardAction extends DefaultBaseAction {

    private final int successfulPage;
    private final int failedPage;

    public InsertCardAction(int successfulPage, int failedPage) {
        this.successfulPage = successfulPage;
        this.failedPage = failedPage;
    }

    @Override
    protected void installButtonAction() {
        JButton btn = (JButton) this.owner;
        btn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                waitCommunicationTask(new ReadCardTask(), true);
            }
        });
    }

    private class ReadCardTask extends SwingWorker<String, String> {

        @Override
        protected String doInBackground() throws Exception {
            try {
                Thread.sleep(3000);
//            throw new Exception();
                panelJump(successfulPage);
                return "1234567";
            } catch (Exception exception) {
                Logger.getLogger(InsertCardAction.class.getName()).log(Level.SEVERE, null, exception);
                panelJump(failedPage);
                throw exception;
            }
        }
    }
}
