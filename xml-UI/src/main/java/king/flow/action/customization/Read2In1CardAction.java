/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.flow.action.customization;

import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import javax.swing.JComponent;
import javax.swing.SwingWorker;
import king.flow.action.business.ReadCardAction;
import static king.flow.common.CommonUtil.getLogger;
import static king.flow.common.CommonUtil.getResourceMsg;
import static king.flow.common.CommonUtil.showMsg;
import static king.flow.common.CommonUtil.check2In1Card;

/**
 *
 * @author liujin
 */
public class Read2In1CardAction extends ReadCardAction {

    public Read2In1CardAction(int nextFocus) {
        super(nextFocus);
    }

    @Override
    protected void readCard(String value) {
        waitCommunicationTask(new Swipe2In1CardTask(value));
    }

    private class Swipe2In1CardTask extends SwingWorker<String, Integer> {

        private final String actionCommand;

        public Swipe2In1CardTask(String actionCommand) {
            this.actionCommand = actionCommand;
        }

        @Override
        protected String doInBackground() throws Exception {
            Thread.sleep(1000);
            switch (actionCommand) {
                case "ACTION4":
                    int cardReading = check2In1Card();
                    break;
                default:
                    getLogger(Swipe2In1CardTask.class.getName()).log(Level.WARNING,
                            "Unknown swipe card action {0} happens in 2in1 reading device", actionCommand);
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
