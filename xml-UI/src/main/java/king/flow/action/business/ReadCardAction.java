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
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.UIDefaults;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import king.flow.action.DefaultAction;
import static king.flow.common.CommonUtil.getLogger;
import static king.flow.common.CommonUtil.getResourceMsg;
import static king.flow.common.CommonUtil.showMsg;
import static king.flow.common.CommonUtil.swipeICCard;
import static king.flow.common.CommonUtil.swipeMagnetCard;
import king.flow.view.Action.SwipeCardAction;

/**
 *
 * @author LiuJin
 */
public class ReadCardAction extends DefaultAction<JComboBox> {

    protected final int nextFocus;
    protected final boolean editable;
    private final SwipeCardAction.Debug debugMode;

    public ReadCardAction(int nextFocus) {
        this.nextFocus = nextFocus;
        this.editable = true;
        this.debugMode = null;
    }

    public ReadCardAction(int nextFocus, boolean editable) {
        this.nextFocus = nextFocus;
        this.editable = editable;
        this.debugMode = null;
    }

    public ReadCardAction(int nextFocus, SwipeCardAction.Debug debugMode) {
        this.nextFocus = nextFocus;
        this.editable = true;
        this.debugMode = debugMode;
    }

    public ReadCardAction(int nextFocus, boolean editable, SwipeCardAction.Debug debugMode) {
        this.nextFocus = nextFocus;
        this.editable = editable;
        this.debugMode = debugMode;
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
                        readCard(value);
                    }
                }
            }

        });
    }

    protected void readCard(String value) {
        waitCommunicationTask(new SwipeCardTask(value));
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
            limitUserInput();
        } else {
            owner.setEditable(false);
        }
    }

    protected void limitUserInput() {
        if (debugMode == null || debugMode.isLimit()) {
            setEditorDocument();
        }
    }

    protected void setEditorDocument() {
        JTextField editor = (JTextField) owner.getEditor().getEditorComponent();
        editor.setDocument(new PlainDocument() {
            @Override
            public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
                if (str == null) {
                    return;
                }

                if (str.length() < 2) {
                    return;
                }
                super.insertString(offset, str, attr);
            }

            @Override
            public void remove(int offs, int len) throws BadLocationException {
                if (len < 2) {
                    return;
                }
                super.remove(offs, len);
            }
        });
    }

    private class SwipeCardTask extends SwingWorker<String, Integer> {

        private final String actionCommand;

        public SwipeCardTask(String actionCommand) {
            this.actionCommand = actionCommand;
        }

        @Override
        protected String doInBackground() throws Exception {
            Thread.sleep(1000);
            if (debugMode != null) {
                return debugMode.getCardId();
            }
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
