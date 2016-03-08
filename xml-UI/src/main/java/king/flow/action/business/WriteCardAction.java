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
import static king.flow.common.CommonUtil.createTLSMessage;
import static king.flow.common.CommonUtil.getLogger;
import static king.flow.common.CommonUtil.getResourceMsg;
import static king.flow.common.CommonUtil.parseTLSMessage;
import static king.flow.common.CommonUtil.sendMessage;
import king.flow.control.driver.ICCardConductor;
import king.flow.data.TLSResult;
import king.flow.view.MsgSendAction;
import king.flow.view.Rules;

/**
 *
 * @author Administrator
 */
public class WriteCardAction extends BalanceTransAction {

    public WriteCardAction(String prsCode, int cmdCode, List<String> conditionList,
            MsgSendAction.NextStep next, MsgSendAction.Exception expPage, Rules checkRules) {
        super(prsCode, cmdCode, conditionList, next, expPage, checkRules);
    }

    @Override
    protected void send() {
        if (!validateRules()) {
            return;
        }

        waitCommunicationTask(new WriteCardTask());
    }

    private class WriteCardTask extends SwingWorker<String, Integer> {

        @Override
        protected String doInBackground() throws Exception {
            Thread.sleep(1000);
            Map<Integer, String> conditionValues = retrieveConditionValues();
            String msg = createTLSMessage(prsCode, conditionValues);
            getLogger(WriteCardTask.class.getName()).log(Level.INFO,
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
                getLogger(WriteCardTask.class.getName()).log(Level.INFO,
                        "Retrieve raw response from server : \n{0}", resp);
            } else {
                getLogger(WriteCardTask.class.getName()).log(Level.WARNING,
                        "Retrieve nothing from server");
                //launch strike-balance for previous transaction timeout
                String strike_balance = buildBalancedMsg(conditionValues, msg);
                getLogger(WriteCardTask.class.getName()).log(Level.INFO,
                        "Sending balanced transaction TLS Message for timeout : \n{0}", strike_balance);
                try {
                    if (cmdCode < 0) {
                        resp = sendMessage(strike_balance);
                    } else {
                        resp = sendMessage(cmdCode, strike_balance);
                    }
                } catch (Exception exception) {
                    getLogger(WriteCardTask.class.getName()).log(Level.WARNING,
                            "Fail to send strike-balance message due to : {0}", exception.getMessage());
                    throw exception;
                } finally {
                    showErrMsg(Integer.MIN_VALUE, getResourceMsg("terminal.no.response.prompt"));
                }

                return resp;
            }

            TLSResult result = parseTLSMessage(resp);
            getLogger(WriteCardTask.class.getName()).log(Level.INFO,
                    "Transform transaction response to : \n{0}", result);
            if (result == null) {
                getLogger(WriteCardTask.class.getName()).log(Level.INFO,
                        "Fail to transform response and receive invalid raw response : \n{0}", resp);
                showErrMsg(Integer.MIN_VALUE, getResourceMsg("terminal.invalidated.response.prompt"));
                return resp;
            } else if (result.getRetCode() != 0) {
                final String errMsg = result.getErrMsg();
                getLogger(WriteCardTask.class.getName()).log(Level.INFO,
                        "Operation action failed with retcode {0}, root cause : \n{1}",
                        new Object[]{result.getRetCode(), errMsg});
                showErrMsg(Integer.MIN_VALUE, (errMsg == null || errMsg.length() == 0)
                        ? getResourceMsg("terminal.failed.operation.prompt") : errMsg);
                return resp;
            }

            //TODO: invoke card writing interface and save data into card chip
            //
            try {
                new ICCardConductor().readCard("1", "");
            } catch (Exception e) {
                getLogger(WriteCardTask.class.getName()).log(Level.WARNING,
                        "Fail to write card due to : \n{0}", e);
                //launch strike-balance for card writing failure
                String strike_balance = buildBalancedMsg(conditionValues, msg);
                getLogger(WriteCardTask.class.getName()).log(Level.INFO,
                        "Sending balanced transaction TLS Message for card-writing failure: \n{0}", strike_balance);
                try {
                    if (cmdCode < 0) {
                        resp = sendMessage(strike_balance);
                    } else {
                        resp = sendMessage(cmdCode, strike_balance);
                    }
                } catch (Exception exception) {
                    getLogger(WriteCardTask.class.getName()).log(Level.WARNING,
                            "Fail to send strike-balance message due to : {0}", exception.getMessage());
                    throw exception;
                } finally {
                    showErrMsg(Integer.MIN_VALUE, getResourceMsg("operation.card.write.error"));
                }

                return resp;
            }

            //be successful and show final result to user
            if (next == null) {
                getLogger(WriteCardTask.class.getName()).log(Level.WARNING,
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
