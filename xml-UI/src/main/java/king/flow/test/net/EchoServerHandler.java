/*
 * Copyright 2012 The Netty Project
 *
 * The Netty Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package king.flow.test.net;

import com.github.jsonj.JsonElement;
import com.github.jsonj.JsonObject;
import com.github.jsonj.tools.JsonParser;
import com.github.jsonj.tools.JsonSerializer;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;

import java.util.logging.Level;
import java.util.logging.Logger;
import king.flow.common.CommonConstants;
import static king.flow.common.CommonConstants.UTF8;
import static king.flow.common.CommonConstants.XML_NODE_PREFIX;

/**
 * Handler implementation for the echo server.
 */
@Sharable
public class EchoServerHandler extends ChannelInboundHandlerAdapter {

    private static final Logger logger = Logger.getLogger(
            EchoServerHandler.class.getName());
    private static String dataPath = "./mock-data/server.json";
    private JsonObject jsonRoot;
    private Set<String> invalidAccount;
    private static final String DOT = ".";
    private static final String DOT_SPLIT_PATTERN = "\\.";

    public EchoServerHandler() throws FileNotFoundException, IOException {
        initialData();
    }

    private void initialData() throws IOException {
        JsonParser jsonParser = new JsonParser();
        jsonRoot = jsonParser.parse(new InputStreamReader(new FileInputStream(dataPath), UTF8)).asObject();
//        jsonRoot = jsonParser.parse(new FileReader(dataPath)).asObject();
        invalidAccount = new TreeSet<>();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf bb = (ByteBuf) msg;
        int code = bb.readInt();
        logger.log(Level.INFO, "retcode : {0}", code);
        int length = bb.readInt();
        logger.log(Level.INFO, "packet length : {0}", length);
        final String request = bb.readBytes(length).toString(Charset.forName("utf-8"));
        logger.log(Level.INFO, "packet content : \n{0}", request);

        String terminal = readTerminalId(request);
        String terminalPrefix = new StringBuilder("terminal").append(DOT)
                .append(terminal).append(DOT).toString();

        String prsCode = readPrsCode(request);

        String prsCodePrefix = new StringBuilder(terminalPrefix)
                .append("prscode").append(DOT)
                .append(prsCode).append(DOT).toString();

        String pwdPrefix = new StringBuilder(terminalPrefix)
                .append("pwdVerify").append(DOT).toString();

        String operationPrefix = new StringBuilder(prsCodePrefix)
                .append("operation").append(DOT).toString();

        //whether operation need to account privilege
        if (!prsCode.equalsIgnoreCase("accoutGuashi")) {
            for (String accountName : invalidAccount) {
                String actNameMark = ">" + accountName + "</";
                if (request.contains(actNameMark)) {
                    StringBuilder actHaltMsgCondition = new StringBuilder(terminalPrefix)
                            .append("accountHalt").append(DOT).append("errmsg");
                    String actHaltErrMsg = readJsonElement(actHaltMsgCondition).asPrimitive().asString();
                    ctx.write(Unpooled.copiedBuffer(MockServerResp.mockFailedGeneralResp(terminal, actHaltErrMsg).getBytes(UTF8)));
                    return;
                }
            }
        }

        //whether operation need to verify password
        StringBuilder verifyPwdQuery = new StringBuilder(operationPrefix).append("verifyPwd");
        boolean needPwdVerification = needPwdVerification(verifyPwdQuery);
        if (needPwdVerification) {
            StringBuilder pwdCondition = new StringBuilder(pwdPrefix).append("password");
            String password = readJsonElement(pwdCondition).asPrimitive().asString();
            String pwdMark = ">" + password + "</";
            if (!request.contains(pwdMark)) {
                StringBuilder pwdErrMsgCondition = new StringBuilder(pwdPrefix).append("errmsg");
                String pwdErrMsg = readJsonElement(pwdErrMsgCondition).asPrimitive().asString();
                ctx.write(Unpooled.copiedBuffer(MockServerResp.mockFailedGeneralResp(terminal, pwdErrMsg).getBytes(UTF8)));
                return;
            }
        }

        // whether operation is password-change
        StringBuilder pwdChangeOpQuery = new StringBuilder(operationPrefix).append("resetPwd");
        boolean isPwdChangeOp = isPwdChangeOp(pwdChangeOpQuery);
        if (isPwdChangeOp) {
            changePassword(pwdChangeOpQuery, pwdPrefix, request);
        }

        // whether operation is account-freeze
        StringBuilder actFreezeOpQuery = new StringBuilder(operationPrefix).append("freezeAcount");
        boolean isActFreezeOp = isActFreezeOp(actFreezeOpQuery);
        if (isActFreezeOp) {
            haltAccount(actFreezeOpQuery, request);
        }

        // whether operation is account-halt
        StringBuilder actSuspendOpQuery = new StringBuilder(operationPrefix).append("suspendAcount");
        boolean isActSuspendOp = isActSuspendOp(actSuspendOpQuery);
        if (isActSuspendOp) {
            haltAccount(actSuspendOpQuery, request);
        }

        // whether operation is advancedTable query
        StringBuilder advancedTableOpQuery = new StringBuilder(operationPrefix).append("advancedTableQuery");
        boolean isAdTableQueryOp = isAdTableQueryOp(advancedTableOpQuery);
        String pageNum = null;
        if (isAdTableQueryOp) {
            pageNum = readAdTableQueryPageNum(advancedTableOpQuery, request);
        }

        StringBuilder condition = new StringBuilder(prsCodePrefix)
                .append("success").append(DOT)
                .append("okmsg");
        JsonElement data = readJsonElement(condition);
        String response = null;
        if (data.isObject()) {
            if (pageNum != null) {
                response = data.asObject().get(pageNum).toString();
            } else {
                response = data.asObject().toString();
            }
        } else if (data.isArray()) {
            response = JsonSerializer.serialize(data);
        } else if (data.isPrimitive()) {
            response = data.asPrimitive().toString();
        }
//        String response = jsonRoot.getString("terminal", terminal, "prscode", prsCode, "success", "okmsg");
        logger.log(Level.INFO, "json query result by condition: {0} :\n{1}", new Object[]{condition, response});

        condition = new StringBuilder(prsCodePrefix)
                .append("success").append(DOT)
                .append("prtmsg");
        data = readJsonElement(condition);
        String prtMsg = null;
        if (data != null) {
            if (data.isObject()) {
                prtMsg = data.asObject().toString();
            } else if (data.isArray()) {
                prtMsg = JsonSerializer.serialize(data);
            } else if (data.isPrimitive()) {
                prtMsg = data.asPrimitive().toString();
            }
        }
//        String response = jsonRoot.getString("terminal", terminal, "prscode", prsCode, "success", "okmsg");
        logger.log(Level.INFO, "json query result by condition: {0} :\n{1}", new Object[]{condition, prtMsg});

        condition = new StringBuilder(prsCodePrefix)
                .append("success").append(DOT)
                .append("redirection");
        data = readJsonElement(condition);
        String redirection = null;
        if (data != null) {
            if (data.isObject()) {
                redirection = data.asObject().toString();
            } else if (data.isArray()) {
                redirection = JsonSerializer.serialize(data);
            } else if (data.isPrimitive()) {
                redirection = data.asPrimitive().toString();
            }
        }
//        String response = jsonRoot.getString("terminal", terminal, "prscode", prsCode, "success", "okmsg");
        logger.log(Level.INFO, "json query result by condition: {0} :\n{1}", new Object[]{condition, redirection});

        //this part code is for mocking timeout testcase
        StringBuilder opsTimeSpend = new StringBuilder(operationPrefix).append("timeSpend");
        final JsonElement timeJsonElement = readJsonElement(opsTimeSpend);
        if (timeJsonElement != null) {
            int timeSpending = timeJsonElement.asPrimitive().asInt();
            logger.log(Level.INFO, "[{0}] operation will take {1}seconds to complete",
                    new Object[]{prsCode, timeSpending});
            Thread.sleep(TimeUnit.SECONDS.toMillis(timeSpending));
        }

        switch (code) {
            case CommonConstants.REGISTRY_MSG_CODE:
//                int v = counter.incrementAndGet();
//                if (v % 5 == 0) {
//                    ctx.write(Unpooled.copiedBuffer(MockServerResp.mockUpdateRegistryResp().getBytes("utf-8")));
//                } else {
//                    ctx.write(Unpooled.copiedBuffer(MockServerResp.mockRegistryResp().getBytes("utf-8")));
//                }
                if (response != null) {
                    ctx.write(Unpooled.copiedBuffer(
                            MockServerResp.mockSuccessfulRegistryResp(terminal, response).getBytes(UTF8)));
                } else {
                    //
                }
                break;
            case CommonConstants.KEY_DOWNLOAD_MSG_CODE:
                ctx.write(Unpooled.copiedBuffer(
                        MockServerResp.mockSuccessfulDownloadKeyResp(terminal, response).getBytes(UTF8)));
                break;
            default:
                ctx.write(Unpooled.copiedBuffer(MockServerResp
                        .mockSuccessfulGeneralResp(terminal, response, prtMsg, redirection)
                        .getBytes(UTF8)));
        }
    }

    private boolean needPwdVerification(StringBuilder verifyPwdQuery) {
        JsonElement verifyPwdCondition = readJsonElement(verifyPwdQuery);
        if (verifyPwdCondition != null) {
            return verifyPwdCondition.asPrimitive().asBoolean();
        }
        return true;
    }

    private boolean isPwdChangeOp(StringBuilder pwdChangeOpQuery) {
        JsonElement resetPwdCondition = readJsonElement(pwdChangeOpQuery);
        return resetPwdCondition != null;
    }

    private void changePassword(StringBuilder pwdChangeOpQuery, String pwdPrefix, String request) {
        StringBuilder newPwdQuery = new StringBuilder(pwdChangeOpQuery).append(DOT).append("newPwdComponentId");
        JsonElement newPwdCompId = readJsonElement(newPwdQuery);
        if (newPwdCompId != null) {
            String componentId = newPwdCompId.asPrimitive().toString();
            writePassword(pwdPrefix, componentId, request);
        } else {
            logger.log(Level.WARNING, "JSON file error : no password component id configurated for key '{0}'", newPwdQuery);
        }
    }

    private void writePassword(String pwdPrefix, String pwdComponentId, String request) {
        String newPassword = readTagValue(pwdComponentId, request);
        if (newPassword != null) {
            String pwdKey = pwdPrefix.substring(0, pwdPrefix.length() - 1);
            jsonRoot.get(pwdKey.split(DOT_SPLIT_PATTERN)).asObject().put("password", newPassword);
        }
    }

    private boolean isAdTableQueryOp(StringBuilder advancedTableOpQuery) {
        JsonElement advancedTableQueryCondition = readJsonElement(advancedTableOpQuery);
        return advancedTableQueryCondition != null;
    }

    private String readAdTableQueryPageNum(StringBuilder queryPrefix, final String request) {
        StringBuilder advancedTableQuery = new StringBuilder(queryPrefix).append(DOT).append("componentId");
        JsonElement advancedTableQueryCondition = readJsonElement(advancedTableQuery);
        if (advancedTableQueryCondition != null) {
            String componentId = advancedTableQueryCondition.asPrimitive().toString();
            return readPageNumber(request, componentId);
        } else {
            logger.log(Level.WARNING,
                    "JSON file error : no advanced table page number component id configurated for key '{0}'", advancedTableQuery);
        }
        return null;
    }

    private String readPageNumber(final String request, final String pageComponentId) {
        String pageNumber = readTagValue(pageComponentId, request);
        return pageNumber == null ? "1" : pageNumber;
    }

    private String readPrsCode(final String query) {
        int indexOfPrefix = query.indexOf(PRSCODE_TAG_PREFIX) + PRSCODE_TAG_PREFIX.length();
        int indexOfSuffix = query.indexOf(PRSCODE_TAG_SUFFIX);
        return query.substring(indexOfPrefix, indexOfSuffix);
    }
    private static final String PRSCODE_TAG_SUFFIX = "</prscode>";
    private static final String PRSCODE_TAG_PREFIX = "<prscode>";

    private String readTerminalId(final String query) {
        int indexOfPrefix = query.indexOf(TERMINAL_TAG_PREFIX) + TERMINAL_TAG_PREFIX.length();
        int indexOfSuffix = query.indexOf(TERMINAL_TAG_SUFFIX);
        return query.substring(indexOfPrefix, indexOfSuffix);
    }
    private static final String TERMINAL_TAG_SUFFIX = "</terminalid>";
    private static final String TERMINAL_TAG_PREFIX = "<terminalid>";

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // Close the connection when an exception is raised.
        logger.log(Level.WARNING, "Unexpected exception from downstream.", cause);
        ctx.close();
    }

    private boolean isActFreezeOp(StringBuilder actFreezeOpQuery) {
        JsonElement actFreezeQueryCondition = readJsonElement(actFreezeOpQuery);
        return actFreezeQueryCondition != null;
    }

    private boolean isActSuspendOp(StringBuilder actSuspendOpQuery) {
        JsonElement actSuspendQueryCondition = readJsonElement(actSuspendOpQuery);
        return actSuspendQueryCondition != null;
    }

    private void haltAccount(StringBuilder accountOpQuery, String request) {
        StringBuilder actCompIdQuery = new StringBuilder(accountOpQuery).append(DOT).append("accountComponentId");
        JsonElement actCompId = readJsonElement(actCompIdQuery);
        if (actCompId != null) {
            String componentId = actCompId.asPrimitive().toString();
            final String tagValue = readTagValue(componentId, request);
            if (tagValue != null) {
                if (this.invalidAccount.contains(tagValue)) {
                    this.invalidAccount.remove(tagValue);
                } else {
                    this.invalidAccount.add(tagValue);
                }
            } else {
                logger.log(Level.WARNING, "Cannot read account number by component id '{0}'", componentId);
            }
        } else {
            logger.log(Level.WARNING, "JSON file error : no account component id configurated for key [{0}]", actCompIdQuery);
        }
    }

    private String readTagValue(String componentId, String request) {
        String nodeTagName = XML_NODE_PREFIX + componentId;
        final String TAG_PREFIX = "<" + nodeTagName + ">";
        final String TAG_SUFFIX = "</" + nodeTagName + ">";

        if (!request.contains(TAG_PREFIX) || !request.contains(TAG_SUFFIX)) {
            logger.log(Level.WARNING,
                    "JSON file error : account xml tag {0}{1}  is not comprehensive in request {2}",
                    new Object[]{TAG_PREFIX, TAG_SUFFIX, request});
            return null;
        }
        int indexOfPrefix = request.indexOf(TAG_PREFIX) + TAG_PREFIX.length();
        int indexOfSuffix = request.indexOf(TAG_SUFFIX);
        return request.substring(indexOfPrefix, indexOfSuffix);
    }

    private JsonElement readJsonElement(StringBuilder jsonQuery) {
        try {
            logger.log(Level.INFO, "query json element by condition : {0}", jsonQuery);
            JsonElement value = jsonRoot.get(jsonQuery.toString().split(DOT_SPLIT_PATTERN));
            logger.log(Level.INFO, "query json element get value [{0}] by condition above", value);
            return value;
        } catch (Exception e) {
            logger.log(Level.INFO, "query json element get NULL by condition : {0}", jsonQuery);
            return null;
        }
    }
}
