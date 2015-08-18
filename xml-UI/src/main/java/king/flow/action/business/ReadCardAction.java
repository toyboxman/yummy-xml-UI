/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.flow.action.business;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.SwingWorker;
import javax.swing.UIDefaults;
import king.flow.action.DefaultAction;
import static king.flow.common.CommonUtil.getLogger;
import static king.flow.common.CommonUtil.getResourceMsg;
import static king.flow.common.CommonUtil.showMsg;
import static king.flow.common.CommonUtil.swipeICCard;
import static king.flow.common.CommonUtil.swipeMagnetCard;

/**
 *
 * @author LiuJin
 */
public class ReadCardAction extends DefaultAction<JComboBox> {

    private final int nextFocus;
    private final boolean editable;

    public ReadCardAction(int nextFocus) {
        this.nextFocus = nextFocus;
        this.editable = true;
    }

    public ReadCardAction(int nextFocus, boolean editable) {
        this.nextFocus = nextFocus;
        this.editable = editable;
    }

    @Override
    public void setupListener() {
        owner.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                int stateChange = e.getStateChange();
                if (stateChange == ItemEvent.SELECTED && e.getItemSelectable().getSelectedObjects() != null) {
                    String value = e.getItem().toString();
                    if (value.startsWith("ACTION")) {
                        owner.setSelectedIndex(-1);
                        owner.hidePopup();
                        waitCommunicationTask(new SwipeCardTask(value));
                    }
                }
            }
        });
    }

    @Override
    public void initializeData() {
        if (editable) {
            owner.setEditable(true);
            /**
             * change one component ui configuration
             * http://docs.oracle.com/javase/7/docs/api/javax/swing/plaf/nimbus/package-summary.html
             * http://www.jasperpotts.com/blog/2008/08/skinning-a-slider-with-nimbus/
             *
             */
            UIDefaults ui_conf = new UIDefaults();
            ui_conf.put("ComboBox.squareButton", Boolean.TRUE);
            owner.putClientProperty("Nimbus.Overrides", ui_conf);
            owner.putClientProperty("Nimbus.Overrides.InheritDefaults", false);
//        SwingUtilities.updateComponentTreeUI(owner);
        } else {
            owner.setEditable(false);
        }
    }

    private class SwipeCardTask extends SwingWorker<String, Integer> {

        private final String actionCommand;

        public SwipeCardTask(String actionCommand) {
            this.actionCommand = actionCommand;
        }

        @Override
        protected String doInBackground() throws Exception {
            Thread.sleep(1000);
            switch (actionCommand) {
                case "ACTION1":

                    break;
                case "ACTION2":
                    return swipeMagnetCard();
                case "ACTION3":
                    return swipeICCard();
                default:
                    getLogger(SwipeCardTask.class.getName()).log(Level.WARNING,
                            "Unknown swipe card action happens {0}", actionCommand);
            }
            return null;
        }

        @Override
        protected void done() {
            try {
                String cardNumber = get();
                if (cardNumber == null || cardNumber.length() == 0) {
                    switch (actionCommand) {
                        case "ACTION2":
                            showMsg(owner.getTopLevelAncestor(),
                                    getResourceMsg("operation.magnet.card.read.timeout"));
                            return;
                        case "ACTION3":
                            showMsg(owner.getTopLevelAncestor(),
                                    getResourceMsg("operation.ic.card.read.timeout"));
                            return;
                        default:
                            getLogger(ReadCardAction.class.getName()).log(Level.WARNING,
                                    "Unknown action command '{0}'from swiping card operation", actionCommand);
                    }
                } else {
                    owner.setSelectedItem(cardNumber);
                    if (nextFocus != id) {
                        owner.setFocusable(false); //prevent refocus action from playing wav file
                        getBlock(nextFocus, JComponent.class).requestFocusInWindow(); // switch focus
                        owner.setFocusable(true);  //restore owner focus capability
                    }
                }
            } catch (InterruptedException | ExecutionException ex) {
                getLogger(ReadCardAction.class.getName()).log(Level.SEVERE, "fail to read card information due to error {0}", ex.getMessage());
                showMsg(owner.getTopLevelAncestor(),
                        getResourceMsg("operation.card.read.error"));
            }
        }

    }
}
