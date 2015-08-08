/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.flow.action.customization;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import king.flow.action.DefaultBaseAction;
import king.flow.common.CommonUtil;
import static king.flow.common.CommonUtil.getLogger;

/**
 *
 * @author Administrator
 */
public class FingerPrintDriverAction extends DefaultBaseAction {

    final boolean registryFinerPrint;
    private String printValue;
    final private int nextFocus;

    public FingerPrintDriverAction(int next) {
        this.registryFinerPrint = false;
        this.nextFocus = next;
    }

    public FingerPrintDriverAction(boolean registryFinerPrint, int next) {
        this.registryFinerPrint = registryFinerPrint;
        this.nextFocus = next;
    }

    @Override
    protected void installTextFieldAction() {
        final JTextField jTextField = (JTextField) owner;
        jTextField.addFocusListener(new FocusAdapter() {

            @Override
            public void focusGained(FocusEvent e) {
                new FingerPrintTask().execute();
            }
        });
    }

    private class FingerPrintTask extends SwingWorker<String, Integer> {

        @Override
        protected String doInBackground() throws Exception {
            getLogger(FingerPrintTask.class.getName()).log(Level.CONFIG, "registry value {0}", registryFinerPrint);
            //make media playing done before focus lost due to showing of finger print dialog
            Thread.sleep(TimeUnit.SECONDS.toMillis(3));
            owner.setFocusable(false);
            getBlock(nextFocus, JComponent.class).requestFocusInWindow(); // switch focus
            owner.setFocusable(true);
            
            if (registryFinerPrint) {
                printValue = CommonUtil.registryFingerPrint().trim();
                getLogger(FingerPrintTask.class.getName()).log(Level.INFO, "registry finger print {0}", printValue);
            } else {
                printValue = CommonUtil.readFingerPrint().trim();
                getLogger(FingerPrintTask.class.getName()).log(Level.INFO, "reading finger print {0}", printValue);
            }

            ((JTextField) owner).setText(printValue);
            return printValue;
        }

    }

}
