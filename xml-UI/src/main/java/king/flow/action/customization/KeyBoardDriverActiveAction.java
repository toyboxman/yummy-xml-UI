/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.flow.action.customization;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPasswordField;
import javax.swing.SwingWorker;
import king.flow.action.DefaultAction;
import king.flow.common.CommonUtil;
import static king.flow.common.CommonUtil.inputString;
import static king.flow.common.CommonUtil.getResourceMsg;

/**
 *
 * @author LiuJin
 */
public class KeyBoardDriverActiveAction extends DefaultAction<JPasswordField> {
    
    @Override
    public void setupListener() {
        owner.addFocusListener(new FocusAdapterImpl());
    }
    
    private class FocusAdapterImpl extends FocusAdapter {
        
        @Override
        public void focusGained(FocusEvent e) {
            waitCommunicationTask(new InputTask());
        }
        
    }
    
    private class InputTask extends SwingWorker<String, Integer> {
        
        @Override
        protected String doInBackground() throws Exception {
            Thread.sleep(1000);
            return inputString(owner);
        }
        
        @Override
        protected void done() {
            try {
                String value = get();
                if (value == null) {
                    CommonUtil.showMsg(owner.getTopLevelAncestor(),
                            getResourceMsg("operation.password.input.timeout"));
                } else {
                    owner.setText(value);
                }
            } catch (InterruptedException | ExecutionException ex) {
                Logger.getLogger(KeyBoardDriverActiveAction.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
}
