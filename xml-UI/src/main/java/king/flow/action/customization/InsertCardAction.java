/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.flow.action.customization;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.SwingWorker;
import king.flow.action.DefaultBaseAction;

/**
 *
 * @author liujin
 */
public class InsertCardAction extends DefaultBaseAction{

    @Override
    protected void installButtonAction() {
        JButton btn = (JButton) this.owner;
        btn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                waitCommunicationTask(new ReadCardTask());
            }
        });
    }
    
    private class ReadCardTask extends SwingWorker<String, String> {

        @Override
        protected String doInBackground() throws Exception {
            Thread.sleep(3000);
            return "1234567";
        }
        
    }
}
