/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.flow.action.business;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import king.flow.action.DefaultAction;
import king.flow.common.CommonConstants;
import king.flow.common.CommonUtil;
import static king.flow.common.CommonUtil.getLogger;
import static king.flow.common.CommonUtil.retrieveBankCardAffiliation;
import static king.flow.common.CommonUtil.retrieveCargo;
import king.flow.data.TLSResult;

/**
 *
 * @author LiuJin
 */
public class KeyboardEncryptAction extends DefaultAction<JPasswordField> {

    private final int moneyId;
    private final Integer nextTrigger;
    private final Integer cancelTrigger;
    private ReadEncryptionTask readEncryptionTask;

    public KeyboardEncryptAction(int moneyId, Integer nextTrigger, Integer cancelTrigger) {
        this.moneyId = moneyId;
        this.nextTrigger = nextTrigger;
        this.cancelTrigger = cancelTrigger;
    }

    @Override
    public void setupListener() {
        owner.addFocusListener(new FocusAdapterImpl());
    }

    private class FocusAdapterImpl extends FocusAdapter {

        @Override
        public void focusGained(FocusEvent e) {
            String bankNum = CommonUtil.retrieveCargo(CommonConstants.VALID_BANK_CARD);
            if (bankNum == null) {
                CommonUtil.showErrorMsg(owner.getTopLevelAncestor(),
                        CommonUtil.getResourceMsg("encryption.keyboard.type.precondition.prompt"));
                removeCursor();
                return;
            }
            readEncryptionTask = new ReadEncryptionTask();
            readEncryptionTask.execute();
        }

        @Override
        public void focusLost(FocusEvent e) {
            if (readEncryptionTask != null) {
                readEncryptionTask = null;
            }
        }

    }

    private class ReadEncryptionTask extends SwingWorker<String, Integer> {

        @Override
        protected String doInBackground() throws Exception {
            //if page has been moved, do not prompt user
            if (!owner.isShowing() || !owner.isFocusOwner()) {
                return null;
            }

            if (CommonUtil.openEncryptedKeyboard() != CommonConstants.NORMAL) {
                CommonUtil.showErrorMsg(owner.getTopLevelAncestor(),
                        CommonUtil.getResourceMsg("encryption.keyboard.open.fail.prompt"));
                removeCursor();
                return null;
            }

            String encryption = CommonUtil.readEncryptedString(owner);

            switch (encryption) {
                case CommonConstants.ERROR_ENCRYPTION_TYPE:
                case CommonConstants.TIMEOUT_ENCRYPTION_TYPE:
                case CommonConstants.INVALID_ENCRYPTION_LENGTH:
                    removeCursor();
                    CommonUtil.showMsg(owner.getTopLevelAncestor(),
                            CommonUtil.getResourceMsg(encryption));
                    return null;
                case CommonConstants.QUIT_ENCRYPTION_KEYBOARD:
                    removeCursor();
                    return null;
                case CommonConstants.CANCEL_ENCRYPTION_KEYBOARD:
                    removeCursor();
                    if (cancelTrigger != null) {
                        JButton cancel = getBlock(cancelTrigger, JButton.class);
                        cancel.doClick();
                    }
                    return null;
                default:
                    getLogger(KeyboardEncryptAction.class.getName()).log(Level.INFO,
                            "get valid encryption[{0}] of keyboard", encryption);
                    break;
            }

            CommonUtil.putCargo(Integer.toString(id), encryption);

            String cardNum = retrieveCargo(CommonConstants.VALID_BANK_CARD);
            String cardType = retrieveBankCardAffiliation(cardNum);
            if (cardType.equals(CommonConstants.CARD_AFFILIATION_EXTERNAL)) {
                String calculatedMAC = CommonUtil.calculateMAC(
                        CommonConstants.UNION_PAY_TRANSACTION,
                        CommonUtil.retrieveCargo(TLSResult.UNIONPAY_CARD_INFO),
                        encryption,
                        getBlock(moneyId, JTextField.class).getText());
                if (calculatedMAC != null && calculatedMAC.length() > 0) {
                    CommonUtil.putCargo(TLSResult.UNIONPAY_MAC_INFO, calculatedMAC);
                }
                
                String balancedMAC = CommonUtil.calculateMAC(
                        CommonConstants.UNION_PAY_TRANSACTION_BALANCE,
                        CommonUtil.retrieveCargo(TLSResult.UNIONPAY_CARD_INFO),
                        encryption,
                        getBlock(moneyId, JTextField.class).getText());
                if (balancedMAC != null && balancedMAC.length() > 0) {
                    CommonUtil.putCargo(CommonConstants.BALANCED_PAY_MAC, balancedMAC);
                }
            }

            return encryption;
        }

        @Override
        protected void done() {
            try {
                CommonUtil.closeEncryptedKeyboard();
                String credentials = get();
                if (credentials != null && credentials.length() > 0 && nextTrigger != null) {
                    JButton next = getBlock(nextTrigger, JButton.class);
                    next.doClick();
                }
            } catch (InterruptedException | ExecutionException ex) {
                getLogger(KeyboardEncryptAction.class.getName()).log(Level.WARNING,
                        "encryption of keyboard is broken due to : \n{0}", ex);
            }
        }
    }

    private void removeCursor() {
        owner.setFocusable(false);
        owner.setFocusable(true);
    }
}
