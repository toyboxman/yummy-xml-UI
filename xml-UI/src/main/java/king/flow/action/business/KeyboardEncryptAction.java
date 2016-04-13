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
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import king.flow.action.DefaultAction;
import king.flow.common.CommonConstants;
import king.flow.common.CommonUtil;
import static king.flow.common.CommonUtil.retrieveBankCardAffiliation;
import static king.flow.common.CommonUtil.retrieveCargo;
import king.flow.data.TLSResult;

/**
 *
 * @author LiuJin
 */
public class KeyboardEncryptAction extends DefaultAction<JPasswordField> {
    
    private final int moneyId;
    private final Integer trigger;
    
    public KeyboardEncryptAction(int moneyId, Integer trigger) {
        this.moneyId = moneyId;
        this.trigger = trigger;
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
                owner.setFocusable(false);
                owner.setFocusable(true);
                return;
            }
            if (CommonUtil.openEncryptedKeyboard() != CommonConstants.NORMAL) {
                CommonUtil.showErrorMsg(owner.getTopLevelAncestor(),
                        CommonUtil.getResourceMsg("encryption.keyboard.open.fail.prompt"));
                owner.setFocusable(false);
                owner.setFocusable(true);
                return;
            }
            new ReadEncryptionTask().execute();
        }
        
        @Override
        public void focusLost(FocusEvent e) {
            CommonUtil.closeEncryptedKeyboard();
        }
        
    }
    
    private class ReadEncryptionTask extends SwingWorker<String, Integer> {
        
        @Override
        protected String doInBackground() throws Exception {
            String encryption = CommonUtil.readEncryptedString(owner);
            //if page has been moved, do not prompt user
            if (!owner.isShowing()) {
                return null;
            }
            if (encryption == null || encryption.length() == 0) {
                CommonUtil.showMsg(owner.getTopLevelAncestor(),
                        CommonUtil.getResourceMsg("encryption.keyboard.type.timeout.prompt"));
                owner.setText("");
                owner.setFocusable(false);
                owner.setFocusable(true);
                return null;
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
            }
            
            return encryption;
        }
        
        @Override
        protected void done() {
            try {
                String credentials = get();
                if (credentials != null && trigger != null) {
                    JButton next = getBlock(trigger, JButton.class);
                    next.doClick();
                }
            } catch (InterruptedException | ExecutionException ex) {
                Logger.getLogger(KeyboardEncryptAction.class.getName()).log(Level.WARNING,
                        "encryption of keyboard is broken due to : \n{0}", ex);
            }
        }
    }
}
