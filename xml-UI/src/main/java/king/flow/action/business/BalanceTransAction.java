/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.flow.action.business;

import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import javax.swing.SwingWorker;
import king.flow.action.DefaultMsgSendAction;
import king.flow.common.CommonConstants;
import static king.flow.common.CommonConstants.UID_AFFIX;
import static king.flow.common.CommonUtil.createTLSMessage;
import static king.flow.common.CommonUtil.getLogger;
import static king.flow.common.CommonUtil.getResourceMsg;
import static king.flow.common.CommonUtil.parseTLSMessage;
import static king.flow.common.CommonUtil.sendMessage;
import king.flow.data.TLSResult;
import king.flow.view.MsgSendAction;
import king.flow.view.Rules;

/**
 *
 * @author liujin
 */
public class BalanceTransAction extends DefaultMsgSendAction {

    public BalanceTransAction(String prsCode, int cmdCode, List<String> conditionList,
            MsgSendAction.NextStep next, MsgSendAction.Exception expPage, Rules checkRules) {
        super(prsCode, cmdCode, conditionList, next, expPage, checkRules);
    }

    @Override
    protected void send() {
        if (!validateRules()) {
            return;
        }

        waitCommunicationTask(new BalanceTRXTask());
    }

    protected String buildBalancedMsg(Map<Integer, String> conditionValues, String previousMsg) {
        String balancedMsg = createTLSMessage(prsCode, conditionValues);
        int start = previousMsg.indexOf(CommonConstants.UID_PREFIX);
        int end = previousMsg.indexOf(UID_AFFIX) + UID_AFFIX.length();
        String bid = previousMsg.substring(start, end);
        bid = bid.replaceAll(TLSResult.UID, CommonConstants.BID);
        int insertPoint = balancedMsg.indexOf(UID_AFFIX) + UID_AFFIX.length();
        balancedMsg = new StringBuilder(balancedMsg).insert(insertPoint, bid).toString();
        balancedMsg = balancedMsg.replace(prsCode, CommonConstants.REVERT + prsCode);
        return balancedMsg;
    }

    private class BalanceTRXTask extends SwingWorker<String, Integer> {

        @Override
        protected String doInBackground() throws Exception {
            Thread.sleep(1000);
            Map<Integer, String> conditionValues = retrieveConditionValues();
            String msg = createTLSMessage(prsCode, conditionValues);
            getLogger(BalanceTRXTask.class.getName()).log(Level.INFO,
                    "Sending transaction TLS Message : \n{0}", msg);
            String resp = null;
            try {
                if (cmdCode < 0) {
                    resp = sendMessage(msg);
                } else {
                    resp = sendMessage(cmdCode, msg);
                }
            } catch (Exception exception) {
                showErrMsg(Integer.MIN_VALUE, getResourceMsg("network.unaccessed.prompt"));
                throw exception;
            }

            if (resp != null) {
                resp = resp.trim();
                getLogger(BalanceTRXTask.class.getName()).log(Level.INFO,
                        "Retrieve raw response from server : \n{0}", resp);
            } else {
                getLogger(BalanceTRXTask.class.getName()).log(Level.WARNING,
                        "Retrieve nothing from server");
                //launch strike-balance for previous transaction timeout
                String strike_balance = buildBalancedMsg(conditionValues, msg);
                getLogger(BalanceTRXTask.class.getName()).log(Level.INFO,
                        "Sending balanced transaction TLS Message : \n{0}", strike_balance);
                try {
                    if (cmdCode < 0) {
                        resp = sendMessage(strike_balance);
                    } else {
                        resp = sendMessage(cmdCode, strike_balance);
                    }
                } catch (Exception exception) {
                    getLogger(BalanceTRXTask.class.getName()).log(Level.WARNING,
                            "Fail to send strike-balance message due to : {0}", exception.getMessage());
                    throw exception;
                } finally {
                    showErrMsg(Integer.MIN_VALUE, getResourceMsg("terminal.no.response.prompt"));
                }

                return resp;
            }

            TLSResult result = parseTLSMessage(resp);
            getLogger(BalanceTRXTask.class.getName()).log(Level.INFO,
                    "Transform transaction response to : \n{0}", result);
            if (result == null) {
                getLogger(BalanceTRXTask.class.getName()).log(Level.INFO,
                        "Fail to transform response and receive invalid raw response : \n{0}", resp);
                showErrMsg(Integer.MIN_VALUE, getResourceMsg("terminal.invalidated.response.prompt"));
                return resp;
            } else if (result.getRetCode() != 0) {
                final String errMsg = result.getErrMsg();
                getLogger(BalanceTRXTask.class.getName()).log(Level.INFO,
                        "Operation action failed with retcode {0}, root cause : \n{1}",
                        new Object[]{result.getRetCode(), errMsg});
                showErrMsg(Integer.MIN_VALUE, (errMsg == null || errMsg.length() == 0)
                        ? getResourceMsg("terminal.failed.operation.prompt") : errMsg);
                return resp;
            }

            //be successful and show final result to user
            if (next == null) {
                getLogger(BalanceTRXTask.class.getName()).log(Level.WARNING,
                        "No next display page is configured, show result in current page");
                showResult(result);
            } else {
                //show msg to dedicated component
                showDoneMsg(result);
            }

            return resp;
        }
    }
}
