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
 * @author Administrator
 */
public class WriteCardAction extends DefaultMsgSendAction {

    public WriteCardAction(String prsCode, int cmdCode, List<String> conditionList,
            MsgSendAction.NextStep next, MsgSendAction.Exception expPage, Rules checkRules) {
        super(prsCode, cmdCode, conditionList, next, expPage, checkRules);
    }

    @Override
    protected void send() {
        if (rules != null && !checkNotNull()) {
            return;
        }

        if (rules != null && !checkTemplate()) {
            return;
        }

        if (rules != null && !checkCJK()) {
            return;
        }

        if (rules != null && !checkEqualConditions()) {
            return;
        }

        if (rules != null && !checkNotEqualConditions()) {
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
            getLogger(WriteCardTask.class.getName()).log(Level.INFO, "Sending writing card TLS Message : \n{0}", msg);
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
                getLogger(WriteCardTask.class.getName()).log(Level.INFO, "Retrieve response from server : {0}", resp);
            } else {
                getLogger(WriteCardTask.class.getName()).log(Level.INFO, "Retrieve nothing from server");
                showErrMsg(Integer.MIN_VALUE, getResourceMsg("terminal.no.response.prompt"));
                return resp;
            }

            TLSResult result = parseTLSMessage(resp);
            getLogger(WriteCardTask.class.getName()).log(Level.INFO,
                    "Transform response to : \n{0}", result);
            if (result == null) {
                getLogger(WriteCardTask.class.getName()).log(Level.INFO,
                        "Receive invalid result {0} from server", resp);
                showErrMsg(Integer.MIN_VALUE, getResourceMsg("terminal.invalidated.response.prompt"));
                return resp;
            } else {
                if (result.getRetCode() != 0) {
                    final String errMsg = result.getErrMsg();
                    getLogger(WriteCardTask.class.getName()).log(Level.INFO,
                            "Operation action failed with retcode {0}, root cause {1}",
                            new Object[]{result.getRetCode(), errMsg});
                    showErrMsg(Integer.MIN_VALUE, (errMsg == null || errMsg.length() == 0)
                            ? getResourceMsg("terminal.failed.operation.prompt") : errMsg);
                    return resp;
                }
            }

            //TODO: invoke card writing interface and save data into card chip
            //
            try {
                throw new Exception("write error happened in IC card chip");
            } catch (Exception e) {
                getLogger(WriteCardTask.class.getName()).log(Level.WARNING,
                        "Fail to write card due to error {0}", e.getMessage());
                showErrMsg(Integer.MIN_VALUE, getResourceMsg("operation.card.write.error"));
                String strike_balance = createTLSMessage(prsCode, conditionValues);
                int start = msg.indexOf(UID_PREFIX);
                int end = msg.indexOf(UID_AFFIX) + UID_AFFIX.length();
                String bid = msg.substring(start, end);
                bid = bid.replaceAll(UID, BID);
                int insertPoint = strike_balance.indexOf(UID_AFFIX) + UID_AFFIX.length();
                strike_balance = new StringBuilder(strike_balance).insert(insertPoint, bid).toString();
                getLogger(WriteCardTask.class.getName()).log(Level.INFO, "TLS Message : {0}", strike_balance);
                try {
                    if (cmdCode < 0) {
                        resp = sendMessage(strike_balance);
                    } else {
                        resp = sendMessage(cmdCode, strike_balance);
                    }
                } catch (Exception exception) {
                    getLogger(WriteCardTask.class.getName()).log(Level.WARNING,
                            "Fail to send stike-balance messge due to {0}", exception.getMessage());
                    throw exception;
                }
                return resp;
            }

//            if (next == null || next.getDisplay() < 0) {
//                Logger.getLogger(WriteCardTask.class.getName()).log(Level.INFO,
//                        "No next display page is setup : {0}", next == null ? next : next.getDisplay());
//                showResult(result);
//            } else {
//                //show msg to dedicated component
//                Object metaNode = getBlockMeta(next.getDisplay());
//                if (metaNode instanceof Component) {
//                    showDoneMsg(result);
//                } else {
//                    Logger.getLogger(WriteCardTask.class.getName()).log(Level.INFO,
//                            "Invalidated display component type : {0}", metaNode.getClass().getName());
//                }
//            }
//
//            return resp;
        }
        private static final String BID = "bid";
        private static final String UID = "uid";
        private static final String UID_AFFIX = "</uid>";
        private static final String UID_PREFIX = "<uid>";

    }
}
