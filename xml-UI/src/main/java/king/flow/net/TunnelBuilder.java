package king.flow.net;

import com.github.jsonj.JsonObject;
import com.github.jsonj.tools.JsonParser;
import java.awt.Window;
import java.io.File;
import java.nio.charset.Charset;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.zip.ZipFile;
import javax.xml.bind.JAXBException;
import static king.flow.common.CommonConstants.DOWNLOAD_KEY_SIGNAL;
import king.flow.design.NetConfProcessor;
import static king.flow.common.CommonUtil.getLogger;
import static king.flow.common.CommonUtil.createRegistryTLSMessage;
import static king.flow.common.CommonConstants.REGISTRY_MSG_CODE;
import static king.flow.common.CommonConstants.RESTART_SIGNAL;
import static king.flow.common.CommonConstants.SUCCESSFUL_MSG_CODE;
import static king.flow.common.CommonConstants.UPDATE_SIGNAL;
import king.flow.common.CommonUtil;
import king.flow.common.CommonUtil.DownloadCipherKey;
import static king.flow.common.CommonUtil.TerminalStatus.RESTART;
import static king.flow.common.CommonUtil.getCurrentView;
import static king.flow.common.CommonUtil.parseRegTLSMessage;
import static king.flow.common.CommonUtil.setTerminalStatus;
import king.flow.control.BankAppStarter;
import king.flow.data.RegistryTLSResult;
import static king.flow.data.RegistryTLSResult.APP_UPDATE_MD5;
import static king.flow.data.RegistryTLSResult.APP_UPDATE_PATH;
import static king.flow.data.RegistryTLSResult.APP_UPDATE_VER;
import static king.flow.data.RegistryTLSResult.APP_UPDATE_START;
import king.flow.net.update.UpdateTools;
import king.flow.net.update.http.HttpUpdateTool;

public class TunnelBuilder {

    private static final String DEFAULT_XML = "./conf/backend.xml";
    private static final String TUNNEL_BUILD_SCHEME = "tunnel.build.scheme";
    private static TunnelBuilder tb = new TunnelBuilder();

    private String hostName;
    private int portNumber;
    private int channelTimeout;
    private String prsCode;
    private String terminalID;
    private String token;
    private String branchID;
    private String unionPayID;
    private int period;
    private volatile int authorization_flag = Integer.MIN_VALUE;
    private Timer loop = null;

    public static TunnelBuilder getTunnelBuilder() {
        if (tb == null) {
            tb = new TunnelBuilder();
        }

        return tb;
    }

    private TunnelBuilder() {
        parseXML(System.getProperty(TUNNEL_BUILD_SCHEME, DEFAULT_XML));
        loop = new Timer(true);
    }

    public void enableHeartBeat() {
        loop.scheduleAtFixedRate(new HeartBeatTask(), 10, period);
    }

    private void parseXML(String XMLPath) {
        try {
            NetConfProcessor ncp = new NetConfProcessor(XMLPath).init();
            Transportation tsp = ncp.parse();
            this.hostName = tsp.getServer().getHost();
            this.portNumber = tsp.getServer().getPort();
            Integer timeout = tsp.getServer().getTimeout();
            this.channelTimeout = (timeout == null) ? 120 : timeout;
            this.prsCode = tsp.getRegistration().getPrsCode();
            this.terminalID = tsp.getRegistration().getTerminalID();
            this.token = tsp.getRegistration().getToken();
            this.unionPayID = tsp.getRegistration().getUnionPayID();
            this.branchID = tsp.getRegistration().getBranchno();
            this.period = tsp.getRegistration().getHeartbeat();
            if (tsp.getMisc() != null) {
                CommonUtil.setBankCardPrefix(tsp.getMisc().getBankCardPrefix());
                CommonUtil.setAllowCPUCard(tsp.getMisc().getAllowCPU());
            }
        } catch (JAXBException ex) {
            getLogger(TunnelBuilder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public TunnelBuilder setHostName(String hostName) {
        this.hostName = hostName;
        return this;
    }

    public TunnelBuilder setPortNumber(int portNumber) {
        this.portNumber = portNumber;
        return this;
    }

    public void setChannelTimeout(int channelTimeout) {
        this.channelTimeout = channelTimeout;
    }

    public int getChannelTimeout() {
        return channelTimeout;
    }

    public String getTerminalID() {
        return terminalID;
    }

    public String getToken() {
        return token;
    }

    public String getUnionPayID() {
        return unionPayID;
    }

    public String getBranchID() {
        return branchID;
    }

    public TunnelBuilder setXML(String filePath) {
        parseXML(filePath);
        return this;
    }

    public Tunnel createTunnel() {
        Tunnel tunnel = null;
        if (authorization_flag < 0) {
            tunnel = new FakeTunnel();
        } else {
            tunnel = new P2PTunnel(hostName, portNumber, channelTimeout);
        }

        return tunnel;
    }

    private void grant(boolean access) {
        if (access) {
            authorization_flag = Integer.MAX_VALUE;
        } else {
            authorization_flag = Integer.MIN_VALUE;
        }
    }

    private class HeartBeatTask extends TimerTask {

        @Override
        public void run() {
            P2PTunnel p2PTunnel = new P2PTunnel(hostName, portNumber, channelTimeout);
            final String registryTLSMessage = createRegistryTLSMessage(prsCode, terminalID, token);
            getLogger(HeartBeatTask.class.getName()).log(Level.INFO, "Heartbeat message : {0}", registryTLSMessage);
            try {
                String response = p2PTunnel.connect(REGISTRY_MSG_CODE, registryTLSMessage);
                if (response == null || response.length() == 0) {
                    grant(false);
                    getLogger(HeartBeatTask.class.getName()).log(Level.WARNING,
                            "Cannot receive registration response");
                } else {
                    RegistryTLSResult result = parseRegTLSMessage(response);
                    if (result.getRetCode() == SUCCESSFUL_MSG_CODE) {
                        grant(true);
                        int appRestartSignal = result.getAppRestartSignal();
                        int downloadKeySignal = result.getDownloadKeySignal();
                        int systemRestartSignal = result.getSystemRestartSignal();
                        int appUpdateSignal = result.getAppUpdateSignal();
                        String appUpdateFile = result.getAppUpdateFile();
                        String version = null;
                        String appUpdatePath = null;
                        String appUpdateMd5 = null;
                        String appStartName = null;
                        JsonObject updateProperties = null;
                        if (appUpdateFile != null) {
                            JsonParser jsonParser = new JsonParser();
                            updateProperties = jsonParser.parse(appUpdateFile).asObject();
                            version = updateProperties.getString(APP_UPDATE_VER);
                            appUpdatePath = updateProperties.getString(APP_UPDATE_PATH);
                            appUpdateMd5 = updateProperties.getString(APP_UPDATE_MD5);
                            appStartName = updateProperties.getString(APP_UPDATE_START);
                        }
                        StringBuilder sb = new StringBuilder().append('[')
                                .append("systemRestartSignal : ").append(systemRestartSignal).append(",\n")
                                .append("appRestartSignal : ").append(appRestartSignal).append(",\n")
                                .append("downloadKeySignal : ").append(downloadKeySignal).append(",\n")
                                .append("appUpdateSignal : ").append(appUpdateSignal).append(",\n")
                                .append("version : ").append(version).append(",\n")
                                .append("appUpdatePath : ").append(appUpdatePath).append(",\n")
                                .append("appStartName : ").append(appStartName).append(",\n")
                                .append("appUpdateMd5 : ").append(appUpdateMd5)
                                .append(']');
                        getLogger(HeartBeatTask.class.getName()).log(Level.INFO,
                                "Received registration response message \n {0}", sb.toString());
                        if (systemRestartSignal == RESTART_SIGNAL) {
                            //restart operation system
                            getLogger(HeartBeatTask.class.getName()).log(Level.WARNING,
                                    "Try to restart OS now...");
                        } else if (appRestartSignal == RESTART_SIGNAL) {
                            //restart bankapp
                            getLogger(HeartBeatTask.class.getName()).log(Level.WARNING,
                                    "Try to restart application now...");
                            setTerminalStatus(RESTART);
                            Window window = getCurrentView();
                            window.dispose();
                            new BankAppStarter().start();
                        } else if (downloadKeySignal == DOWNLOAD_KEY_SIGNAL) {
                            //download keyboard cipher key again
                            getLogger(HeartBeatTask.class.getName()).log(Level.WARNING,
                                    "Try to download keyboard cipher key now...");
                            new DownloadCipherKey().execute();
                        } else if (appUpdateSignal == UPDATE_SIGNAL) {
                            getLogger(HeartBeatTask.class.getName()).log(Level.WARNING,
                                    "Try to update application now...");
                            HttpUpdateTool updateTool = UpdateTools.getUpdateTool().createHttpUpdateTool(updateProperties);
                            File downloadFile = updateTool.downloadFile(updateTool.getUpdateUrl());
                            boolean isComprehensive = updateTool.checkSumFile(downloadFile, updateTool.getMd5());
                            if (isComprehensive) {
                                Charset GB2312 = Charset.forName("GB2312");
                                boolean success = updateTool.updateApp(new ZipFile(downloadFile, GB2312));
                                if (success) {
                                    getLogger(HeartBeatTask.class.getName()).log(Level.INFO,
                                            "Succeed in updating application, new application entry is :\n{0}",
                                            updateTool.getUpdateFolderPath());
                                    System.exit(0);
                                } else {
                                    getLogger(HeartBeatTask.class.getName()).log(Level.SEVERE,
                                            "Fail to udpate application due to internal errors");
                                }
                            } else {
                                getLogger(HeartBeatTask.class.getName()).log(Level.SEVERE,
                                        "Fail to download udpate package from {0}", appUpdatePath);
                            }
                        }
                    } else {
                        grant(false);
                        getLogger(HeartBeatTask.class.getName()).log(Level.INFO,
                                "Fail to get authorization from server for {0}", result.getErrMsg());
                    }
                }
            } catch (Exception e) {
                getLogger(HeartBeatTask.class.getName()).log(Level.SEVERE,
                        "Fail to do heart beat communication due to : \n{0}", e);
                grant(false);
            }
        }

    }

}
