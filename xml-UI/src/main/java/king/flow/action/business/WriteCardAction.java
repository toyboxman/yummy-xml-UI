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
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingWorker;
import king.flow.common.CommonConstants;
import king.flow.common.CommonUtil;
import static king.flow.common.CommonUtil.createTLSMessage;
import static king.flow.common.CommonUtil.getLogger;
import static king.flow.common.CommonUtil.getResourceMsg;
import static king.flow.common.CommonUtil.parseTLSMessage;
import king.flow.control.driver.GzCardConductor;
import king.flow.data.TLSResult;
import king.flow.view.DeviceEnum;
import king.flow.view.MsgSendAction;
import king.flow.view.Rules;
import static king.flow.common.CommonUtil.sendMessage;

/**
 *
 * @author Administrator
 */
public class WriteCardAction extends BalanceTransAction {

    private final DeviceEnum cardType;

    public WriteCardAction(DeviceEnum cardType, String prsCode, int cmdCode, List<String> conditionList,
            MsgSendAction.NextStep next, MsgSendAction.Exception expPage, Rules checkRules) {
        super(prsCode, cmdCode, conditionList, next, expPage, checkRules);
        this.cardType = cardType;
    }

    @Override
    protected void send() {
        if (!validateRules()) {
            return;
        }

        switch (cardType) {
            case GZ_CARD:
                waitCommunicationTask(new GZWriteCardTask());
                break;
            default:
                getLogger(WriteCardAction.class.getName()).log(Level.WARNING,
                        "Unsupported card type[{0}] for WriteCardAction", cardType.name());
                String errMsg = getResourceMsg(GzCardConductor.GUOZHEN_CARD_OPERATION_PROMPT);
                showErrMsg(Integer.MIN_VALUE, errMsg);
                panelJump(error.getNextPanel());
        }

    }

    private class GZWriteCardTask extends SwingWorker<String, Integer> {

        @Override
        protected String doInBackground() throws Exception {
            Thread.sleep(1000);
            JsonObject cardInfo = CommonUtil.uncacheCardInfo();
            if (cardInfo == null) {
                getLogger(GZWriteCardTask.class.getName()).log(Level.WARNING,
                        "no cardInfo has been read from gzICCard, this is an invalid cardInfo[{0}]", cardInfo);
                showErrMsg(Integer.MIN_VALUE, getResourceMsg(GzCardConductor.GUOZHEN_CARD_INVALID_PROMPT));
                throw new Exception(GzCardConductor.GUOZHEN_CARD_INVALID_PROMPT);
            }

            String gasSpare = cardInfo.getString(GzCardConductor.CARD_SPARE);
            if (!GzCardConductor.ZERO_GAS_SURPLUS.equals(gasSpare)) {
                getLogger(GZWriteCardTask.class.getName()).log(Level.INFO,
                        "current gas spare amount in card is : \n{0}", gasSpare);
                showErrMsg(Integer.MIN_VALUE, getResourceMsg(GzCardConductor.GUOZHEN_CARD_BUY_PROMPT));
                throw new Exception(GzCardConductor.GUOZHEN_CARD_BUY_PROMPT);
            }

            Map<Integer, String> conditionValues = retrieveConditionValues();
            String msg = createTLSMessage(prsCode, conditionValues);
            getLogger(GZWriteCardTask.class.getName()).log(Level.INFO,
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
                getLogger(GZWriteCardTask.class.getName()).log(Level.INFO,
                        "Retrieve raw response from server : \n{0}", resp);
            } else {
                getLogger(GZWriteCardTask.class.getName()).log(Level.WARNING,
                        "Retrieve nothing from server");
                showErrMsg(Integer.MIN_VALUE, getResourceMsg("terminal.no.response.prompt"));
                return resp;
            }

            TLSResult result = parseTLSMessage(resp);
            getLogger(GZWriteCardTask.class.getName()).log(Level.INFO,
                    "Transform transaction response to : \n{0}", result);
            if (result == null) {
                getLogger(GZWriteCardTask.class.getName()).log(Level.INFO,
                        "Fail to transform response and receive invalid raw response : \n{0}", resp);
                showErrMsg(Integer.MIN_VALUE,
                        getResourceMsg("terminal.invalidated.response.prompt"));
                return resp;
            } else if (result.getRetCode() != CommonConstants.NORMAL) {
                final String errMsg = result.getErrMsg();
                getLogger(GZWriteCardTask.class.getName()).log(Level.INFO,
                        "Operation action failed with retcode {0}, root cause : \n{1}",
                        new Object[]{result.getRetCode(), errMsg});
                if (result.getRetCode() == CommonConstants.BALANCE) {
                    return strikeBalance(conditionValues, msg, getResourceMsg("operation.card.write.error"));
                } else {
                    showErrMsg(Integer.MIN_VALUE, (errMsg == null || errMsg.length() == 0)
                            ? getResourceMsg("terminal.failed.operation.prompt") : errMsg);
                }
                return resp;
            }

            //parse ok message and get something to be written into card
            JsonObject successJson = null;
            JsonElement gasSurplus = null;
            JsonElement gasCount = null;
            JsonElement writeCardCode = null;
            try {
                successJson = new JsonParser().parse(result.getOkMsg()).asObject();
                gasSurplus = successJson.get(GzCardConductor.CARD_SPARE);
                gasCount = successJson.get(GzCardConductor.CARD_GAS_COUNT);
                writeCardCode = successJson.get(GzCardConductor.WRITE_CARD_CODE);
            } catch (Exception e) {
                getLogger(GZWriteCardTask.class.getName()).log(Level.WARNING,
                        "Fail to parse write card info due to : \n{0}", e);
                showErrMsg(Integer.MIN_VALUE,
                        getResourceMsg("terminal.invalidated.response.prompt"));
                throw e;
            }

            try {
                cardInfo.put(GzCardConductor.CARD_SPARE, gasSurplus);
                cardInfo.put(GzCardConductor.CARD_GAS_COUNT, gasCount);
                String cardType = cardInfo.getString(GzCardConductor.CARD_FACTORY);
                if (cardType.equals(GzCardConductor.CARD2_TYPE)
                        || cardType.equals(GzCardConductor.CARD3_TYPE)) {
                    cardInfo.put(GzCardConductor.WRITE_CARD_CODE, writeCardCode);
                }
                int writeResult = CommonConstants.ABNORMAL;
                writeResult = CommonUtil.writeGzICCard(cardInfo);
                if (writeResult != CommonConstants.NORMAL) {
                    throw new Exception("card driver returns failed result : [" + writeResult + "]");
                }
            } catch (Throwable e) {
                getLogger(GZWriteCardTask.class.getName()).log(Level.WARNING,
                        "Fail to write card due to : \n{0}", e.getMessage());
                //launch strike-balance for card writing failure
                return strikeBalance(conditionValues, msg, getResourceMsg("operation.card.write.error"));
            }

            //be successful and show final result to user
            CommonUtil.cleanTranStation(
                    CommonConstants.BALANCED_PAY_MAC);
            TLSResult showResult = new TLSResult(result.getRetCode(),
                    successJson.getString("result"), result.getErrMsg(), result.getPrtMsg());
            if (next == null) {
                getLogger(GZWriteCardTask.class.getName()).log(Level.WARNING,
                        "No next display page is configured, show result in current page");
                showResult(showResult);
            } else {
                //show msg to dedicated component
                showDoneMsg(showResult);
            }

            return resp;
        }

        @Override
        protected void done() {
            try {
                get();
            } catch (InterruptedException | ExecutionException ex) {
                Logger.getLogger(WriteCardAction.class.getName()).log(Level.WARNING,
                        "fail to execute GZWriteCardTask due to : \n{0}", ex.getMessage());
                Logger.getLogger(WriteCardAction.class.getName()).log(Level.INFO,
                        "detailed exception stack is : \n{0}", CommonUtil.dumpExceptionStack(ex));
            }
        }
    }
}
