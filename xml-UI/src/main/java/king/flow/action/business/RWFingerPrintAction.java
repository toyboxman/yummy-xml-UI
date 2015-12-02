/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.flow.action.business;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import king.flow.action.DefaultBaseAction;
import king.flow.common.CommonUtil;
import static king.flow.common.CommonUtil.getLogger;

/**
 *
 * @author Administrator
 */
public class RWFingerPrintAction extends DefaultBaseAction {

    final boolean registryFinerPrint;
    private String printValue;
    final private int nextFocus;

    public RWFingerPrintAction(int next) {
        this.registryFinerPrint = false;
        this.nextFocus = next;
    }

    public RWFingerPrintAction(int next, boolean registryFinerPrint) {
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
        
        jTextField.addAncestorListener(new AncestorListener() {
            @Override
            public void ancestorAdded(AncestorEvent event) {
            }

            @Override
            public void ancestorRemoved(AncestorEvent event) {
//                new FingerPrintTask(true).execute();
            }

            @Override
            public void ancestorMoved(AncestorEvent event) {
            }
        });
    }

    private class FingerPrintTask extends SwingWorker<String, Integer> {
        private final boolean forcedClose;

        public FingerPrintTask() {
            this.forcedClose = false;
        }

        public FingerPrintTask(boolean forcedClose) {
            this.forcedClose = forcedClose;
        }
        
        @Override
        protected String doInBackground() throws Exception {
            if (forcedClose) {
                CommonUtil.closeFingerPrint();
                return "";
            }
            
            //make media playing done before focus lost due to showing of finger print dialog
            Thread.sleep(TimeUnit.SECONDS.toMillis(3));
            owner.setFocusable(false);
            getBlock(nextFocus, JComponent.class).requestFocusInWindow(); // switch focus
            owner.setFocusable(true);
            
            if (registryFinerPrint) {
                printValue = CommonUtil.registryFingerPrint().trim();
                getLogger(FingerPrintTask.class.getName()).log(Level.INFO,
                        "registry finger print {0}", printValue);
            } else {
                printValue = CommonUtil.readFingerPrint().trim();
                getLogger(FingerPrintTask.class.getName()).log(Level.INFO,
                        "reading finger print {0}", printValue);
            }

            ((JTextField) owner).setText(printValue);
            return printValue;
        }

    }

}
