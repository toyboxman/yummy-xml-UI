package king.flow.action.customization;

import java.awt.HeadlessException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import javax.swing.SwingWorker;
import king.flow.action.DefaultMsgSendAction;
import king.flow.common.CommonConstants;
import static king.flow.common.CommonConstants.GENERAL_MSG_CODE;
import static king.flow.common.CommonUtil.showOptionMsg;
import static king.flow.common.CommonUtil.getLogger;
import static king.flow.common.CommonUtil.createTLSMessage;
import static king.flow.common.CommonUtil.parseTLSMessage;
import static king.flow.common.CommonUtil.showMsg;
import static king.flow.common.CommonUtil.showErrorMsg;
import king.flow.data.TLSResult;
import static king.flow.net.TunnelBuilder.getTunnelBuilder;
import king.flow.view.Action;

/**
 *
 * @author LiuJin
 */
public class TransferCashButtonAction extends DefaultMsgSendAction {

    public TransferCashButtonAction(String code, List<String> conditionList,
            Action.MsgSendAction.NextStep next, Action.MsgSendAction.Exception next2) {
        super(code, GENERAL_MSG_CODE, conditionList, next, next2, null);
    }

    @Override
    public void showResult(TLSResult result) throws HeadlessException {
        showOptionMsg(owner.getTopLevelAncestor(), result.getOkMsg(), null, this);
    }

    private class TransferCashWorker extends SwingWorker<String, Integer> {

        @Override
        protected String doInBackground() throws Exception {
            Map<Integer, String> conditionValues = retrieveConditionValues();
            String msg = createTLSMessage(TransferCashButtonAction.this.prsCode, conditionValues);
            String resp = null;
            try {
                resp = getTunnelBuilder().createTunnel().connect(CommonConstants.GENERAL_MSG_CODE, msg);
            } catch (Exception e) {
                getLogger(TransferCashWorker.class.getName()).log(Level.SEVERE,
                        "Networks cannot be accessed normally now", e);
                showErrorMsg(owner.getTopLevelAncestor(), "Networks cannot be accessed normally now");
                throw e;
            }
            return resp;
        }

        @Override
        protected void done() {
            try {
                String value = get();
                if (value != null) {
                    value = value.trim();
                    getLogger(TransferCashWorker.class.getName()).log(Level.INFO, "Retrieve response from server : {0}", value);
                } else {
                    getLogger(TransferCashWorker.class.getName()).log(Level.INFO, "Retrieve no response from server");
                    showErrorMsg(owner.getTopLevelAncestor(), "Receive nothing from server");
                    return;
                }

                TLSResult result = parseTLSMessage(value);
                if (result == null) {
                    getLogger(TransferCashWorker.class.getName()).log(Level.INFO,
                            "Receive invalidated result {0} from server", value);
                    showErrorMsg(owner.getTopLevelAncestor(), "Receive invalidated result from server");
                    return;
                } else {
                    if (result.getRetCode() != 0) {
                        getLogger(TransferCashWorker.class.getName()).log(Level.INFO,
                                "Query action failed with retcode {0}, root cause {1}",
                                new Object[]{result.getRetCode(), result.getErrMsg()});
                        showMsg(owner.getTopLevelAncestor(), result.getErrMsg());
                        return;
                    }
                }

            } catch (InterruptedException | ExecutionException ex) {
                getLogger(TransferCashButtonAction.class.getName()).log(Level.WARNING, null, ex);
            }
        }
    }
}
