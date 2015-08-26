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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingWorker;
import king.flow.action.DefaultBaseAction;
import king.flow.common.CommonUtil;

/**
 *
 * @author liujin
 */
public class InsertCardAction extends DefaultBaseAction {

    private final int successfulPage;
    private final int failedPage;
    private final String animationFile;

    public InsertCardAction(int successfulPage, int failedPage, String animation) {
        this.successfulPage = successfulPage;
        this.failedPage = failedPage;
        this.animationFile = animation;
    }

    @Override
    protected void installButtonAction() {
        JButton btn = (JButton) this.owner;
        btn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                final JDialog progressAnimation = buildAnimationDialog(animationFile);
                final ImageIcon bgImage = CommonUtil.getDefaultBackgroundImage();
                if (bgImage != null) {
                    progressAnimation.getContentPane().add(new JLabel(bgImage), 1);
                } else {
                    progressAnimation.getContentPane().add(new JLabel(), 1);
                }
                
                waitCommunicationTask(new ReadCardTask(), progressAnimation);
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
