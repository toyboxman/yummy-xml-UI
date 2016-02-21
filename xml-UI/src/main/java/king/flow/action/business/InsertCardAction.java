/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.flow.action.business;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.plaf.FontUIResource;
import king.flow.action.DefaultBaseAction;
import king.flow.common.CommonUtil;
import static king.flow.common.CommonUtil.getLogger;
import static king.flow.common.CommonUtil.getResourceMsg;
import static king.flow.common.CommonUtil.getWindowNode;
import king.flow.view.Action.InsertICardAction;
import king.flow.view.Component;
import king.flow.view.UiStyle;
import king.flow.view.Window;

/**
 *
 * @author liujin
 */
public class InsertCardAction extends DefaultBaseAction {

    private final InsertICardAction.NextStep successfulPage;
    private final InsertICardAction.Exception failedPage;
    private final String animationFile;
    private final ArrayList<Integer> successfulDisplay;
    private final ArrayList<Integer> failedDisplay;

    public InsertCardAction(InsertICardAction.NextStep successfulPage, InsertICardAction.Exception failedPage, String animation) {
        this.successfulPage = successfulPage;
        this.failedPage = failedPage;
        this.animationFile = animation;
        ArrayList<String> displayList = CommonUtil.buildListParameters(successfulPage.getDisplay());
        successfulDisplay = new ArrayList<>();
        for (String id : displayList) {
            successfulDisplay.add(Integer.parseInt(id));
        }

        displayList = CommonUtil.buildListParameters(failedPage.getDisplay());
        failedDisplay = new ArrayList<>();
        for (String id : displayList) {
            failedDisplay.add(Integer.parseInt(id));
        }
    }

    protected JLabel progressTip;

    @Override
    protected void installButtonAction() {
        JButton btn = (JButton) this.owner;
        btn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                progressTip = new JLabel(getResourceMsg("operation.ic.card.insert.prompt"));
                Window windowNode = getWindowNode();
                UiStyle uiStyle = windowNode.getUiStyle();
                if (uiStyle != null && uiStyle.getFont() != null && uiStyle.getFont().getName() != null) {
                    progressTip.setFont(new FontUIResource(uiStyle.getFont().getName(), java.awt.Font.BOLD, 50));
                } else {
                    progressTip.setFont(new FontUIResource("Dialog", java.awt.Font.BOLD, 50));
                }
                progressTip.setHorizontalAlignment(SwingConstants.CENTER);
                progressTip.setVerticalAlignment(SwingConstants.BOTTOM);
                final JDialog progressAnimation = buildAnimationDialog(animationFile);
                progressTip.setBounds(0, 120, progressAnimation.getBounds().width, 80);
                progressAnimation.getContentPane().add(progressTip, 1);
                final ImageIcon bgImage = CommonUtil.getImageIcon("/image/2.jpg");
                 //final ImageIcon bgImage = CommonUtil.getDefaultBackgroundImage();
                if (bgImage != null) {
                    progressAnimation.getContentPane().add(new JLabel(bgImage), 2);
                } else {
                    progressAnimation.getContentPane().add(new JLabel(), 2);
                }

                waitCommunicationTask(new ReadCardTask(), progressAnimation);
            }
        });
    }

    private void showOnComponent(int componentId, String value) {
        Component meta = (Component) getBlockMeta(componentId);
        getLogger(InsertCardAction.class.getName()).log(Level.INFO,
                "Display component[{0}] type : {1}",
                new Object[]{String.valueOf(meta.getId()), meta.getType().value()});
        switch (meta.getType()) {
            case LABEL:
                JLabel label = getBlock(meta.getId(), JLabel.class);
                label.setText(value);
                break;
            case TEXT_FIELD:
                JTextField textField = getBlock(meta.getId(), JTextField.class);
                textField.setText(value);
                break;
            case COMBO_BOX:
                JComboBox combo = getBlock(meta.getId(), JComboBox.class);
                if (combo.isEditable()) {
                    combo.getEditor().setItem(value);
                }
                break;
            default:
                getLogger(InsertCardAction.class.getName()).log(Level.WARNING,
                        "Unsupported showed component type : {0}", meta.getType());
        }
    }

    private class ReadCardTask extends SwingWorker<String, String> {

        @Override
        protected String doInBackground() throws Exception {
            try {
                List<String> debug = successfulPage.getDebug();

                if (debug.isEmpty()) {
                    Thread.sleep(2000);
                    throw new Exception("Currently we have no driver :-(");
                } else {
                    //debug mode
                    Thread.sleep(3000);
                    int len = Math.min(debug.size(), successfulDisplay.size());
                    for (int i = 0; i < len; i++) {
                        showOnComponent(successfulDisplay.get(i), debug.get(i));
                    }
                }

                Integer hop = successfulPage.getHop();
                if (hop == null) {
                    panelJump(successfulPage.getNextPanel());
                } else {
                    getBlock(hop, JButton.class).doClick();
                }
                return "Success";
            } catch (Exception exception) {
                getLogger(InsertCardAction.class.getName()).log(Level.SEVERE,
                        "Occur problem during reading IC card, root cause comes from \n{0}", exception.getMessage());
                showOnComponent(failedDisplay.get(0), getResourceMsg("operation.ic.card.read.error"));
                panelJump(failedPage.getNextPanel());
                throw exception;
            }
        }
    }
}
