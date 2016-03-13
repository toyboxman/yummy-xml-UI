/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.flow.action.business;

import com.github.jsonj.JsonElement;
import com.github.jsonj.JsonObject;
import com.github.jsonj.tools.JsonParser;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import javax.swing.SwingWorker;
import king.flow.common.CommonConstants;
import king.flow.common.CommonUtil;
import static king.flow.common.CommonUtil.createTLSMessage;
import static king.flow.common.CommonUtil.getLogger;
import static king.flow.common.CommonUtil.getResourceMsg;
import static king.flow.common.CommonUtil.parseTLSMessage;
import static king.flow.common.CommonUtil.sendMessage;
import king.flow.control.driver.GzCardConductor;
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
                showErrMsg(Integer.MIN_VALUE,
                        getResourceMsg("terminal.invalidated.response.prompt"));
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

            //parse ok message and get something to be written into card
            JsonObject successJson = null;
            JsonElement gasSurplus = null;
            JsonElement gasCount = null;
            try {
                successJson = new JsonParser().parse(result.getOkMsg()).asObject();
                gasSurplus = successJson.get(GzCardConductor.CARD_SPARE);
                gasCount = successJson.get(GzCardConductor.CARD_GAS_COUNT);
            } catch (Exception e) {
                getLogger(WriteCardTask.class.getName()).log(Level.WARNING,
                        "Fail to parse write card info due to : \n{0}", e);
                showErrMsg(Integer.MIN_VALUE,
                        getResourceMsg("terminal.invalidated.response.prompt"));
                throw e;
            }

            try {
                JsonObject cardInfo = CommonUtil.uncacheCardInfo();
                cardInfo.put(GzCardConductor.CARD_SPARE, gasSurplus);
                cardInfo.put(GzCardConductor.CARD_GAS_COUNT, gasCount);
                int writeResult = CommonUtil.writeGzICCard(cardInfo);
                if (writeResult != CommonConstants.NORMAL) {
                    throw new Exception("card driver returns failed result : [" + writeResult + "]");
                }
            } catch (Throwable e) {
                getLogger(WriteCardTask.class.getName()).log(Level.WARNING,
                        "Fail to write card due to : \n{0}", e.getMessage());
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
            TLSResult showResult = new TLSResult(result.getRetCode(),
                    successJson.getString("result"), result.getErrMsg(), result.getPrtMsg());
            if (next == null) {
                getLogger(WriteCardTask.class.getName()).log(Level.WARNING,
                        "No next display page is configured, show result in current page");
                showResult(showResult);
            } else {
                //show msg to dedicated component
                showDoneMsg(showResult);
            }

            return resp;
        }
    }
}
