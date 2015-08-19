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
import king.flow.common.CommonConstants;
import king.flow.common.CommonUtil;
import static king.flow.common.CommonUtil.getLogger;
import static king.flow.common.CommonUtil.getResourceMsg;
import static king.flow.common.CommonUtil.showMsg;
import static king.flow.common.CommonUtil.check2In1Card;
import static king.flow.common.CommonUtil.swipe2In1Card;

/**
 *
 * @author liujin
 */
public class Read2In1CardAction extends ReadCardAction {

    public Read2In1CardAction(int nextFocus) {
        super(nextFocus);
    }

    public Read2In1CardAction(int nextFocus, boolean editable) {
        super(nextFocus, editable);
    }

    @Override
    protected void readCard(String value) {
        waitCommunicationTask(new Swipe2In1CardTask(value));
    }

    private class Swipe2In1CardTask extends SwingWorker<String, Integer> {

        private final String actionCommand;
        int cardReadingState = 0;

        public Swipe2In1CardTask(String actionCommand) {
            this.actionCommand = actionCommand;
        }

        @Override
        protected String doInBackground() throws Exception {
            Thread.sleep(1000);
            switch (actionCommand) {
                case "ACTION4":
                    cardReadingState = check2In1Card();
                    switch (cardReadingState) {
                        case CommonConstants.MAGNET_CARD_STATE:

                            break;
                        case CommonConstants.IC_CARD_STATE:
                            String cardInfo = swipe2In1Card();// driver will blocking thread and wait IC card information return
                            if (cardInfo == null || cardInfo.length() == 0) {
                                //fail to read card information
                                getLogger(Swipe2In1CardTask.class.getName()).log(Level.WARNING,
                                        "No IC card information is read, probably card is invalid", cardReadingState);
                                return null;
                            } else {
                                getLogger(Swipe2In1CardTask.class.getName()).log(Level.INFO,
                                        "Reading information {0} from IC card", cardInfo);
                            }
                            break;
                        case CommonConstants.INVALID_CARD_STATE:
                            break;
                        default:
                            getLogger(Swipe2In1CardTask.class.getName()).log(Level.WARNING,
                                    "Unknown card-checking state {0} happens in 2in1 reading device", cardReadingState);
                    }
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
                if (cardNumber == null) {
                    switch (cardReadingState) {
                        case CommonConstants.MAGNET_CARD_STATE:
                            showMsg(owner.getTopLevelAncestor(),
                                    getResourceMsg("operation.magnet.card.read.timeout"));
                            return;
                        case CommonConstants.IC_CARD_STATE:
                            showMsg(owner.getTopLevelAncestor(),
                                    getResourceMsg("operation.ic.card.read.timeout"));
                            return;
                        default:
                            showMsg(owner.getTopLevelAncestor(),
                                    getResourceMsg("operation.card.read.error"));
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
