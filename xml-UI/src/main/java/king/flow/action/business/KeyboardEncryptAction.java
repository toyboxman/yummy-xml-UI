/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.flow.action.business;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import king.flow.action.DefaultAction;
import king.flow.common.CommonConstants;
import king.flow.common.CommonUtil;
import king.flow.data.TLSResult;

/**
 *
 * @author LiuJin
 */
public class KeyboardEncryptAction extends DefaultAction<JPasswordField> {

    private final int moneyId;

    public KeyboardEncryptAction(int moneyId) {
        this.moneyId = moneyId;
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
            owner.setText("");
        }

    }

    private class ReadEncryptionTask extends SwingWorker<String, Integer> {

        @Override
        protected String doInBackground() throws Exception {
            String encryption = CommonUtil.readEncryptedString(owner);
            if (encryption == null) {
                CommonUtil.showMsg(owner.getTopLevelAncestor(),
                        CommonUtil.getResourceMsg("encryption.keyboard.type.timeout.prompt"));
                owner.setFocusable(false);
                owner.setFocusable(true);
                return null;
            }
            CommonUtil.putCargo(Integer.toString(id), encryption);

            String calculatedMAC = CommonUtil.calculateMAC(
                    "3",
                    CommonUtil.retrieveCargo(TLSResult.UNIONPAY_CARD_INFO),
                    encryption,
                    getBlock(moneyId, JTextField.class).getText(),
                    Long.toString(CommonUtil.tellMeCounter()));
            if (calculatedMAC != null) {
                CommonUtil.putCargo(TLSResult.UNIONPAY_MAC_INFO, calculatedMAC);
            }
            
            return encryption;
        }
    }
}
