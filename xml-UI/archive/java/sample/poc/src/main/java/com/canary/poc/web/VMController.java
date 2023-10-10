package com.canary.poc.web;

import com.canary.poc.entity.VM;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import com.jcraft.jsch.UserInfo;
import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.apache.tomcat.util.codec.binary.Base64;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.StringJoiner;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class VMController {

    public static final String VC = "10.83.46.76";
    public static final String NSX = "10.83.47.148";
    public static final String CONNECTION_CHECK_SH = "~/test/l2/connection-check.sh";
    public static final String MTU_CHECK_SH = "~/test/l2/mtu-check.sh";
    public static final String CONN_RESULT_OUTPUT = "/root/test/l2/result/output";
    public static final String CONN_RESULT_OUTPUT_MORE = "/root/test/l2/result/output-more";
    public static final String PERF_CHECK_SH = "~/test/perf/perf-check.sh";
    public static final String PERF_RESULT_OUTPUT = "/root/test/perf/result/output";
    public static final String CANARY_PHOTON_PREFIX = "canary-photon";
    public static final String PING_START_POINT = CANARY_PHOTON_PREFIX + "-1";
    public static final String CANARY_PASSWORD = "ca$hc0w123";
    public static final String CANARY_USER = "root";
    public static final String NSX_PASSWORD = "u8%HlF5#!uen";
    public static final String NSX_USERNAME = "admin";

    private final TrustManager[] trustAllCerts = new TrustManager[]{
            new X509TrustManager() {
                @Override
                public void checkClientTrusted(X509Certificate[] chain, String authType) {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] chain, String authType) {
                }

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[]{};
                }
            }
    };
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final JSch sch = new JSch();
    private VCToken token;
    private OkHttpClient okHttpClient;
    private String authenticationHeader;
    private String nsxAuthenticationHeader = getAuthenticationHeader(NSX_USERNAME, NSX_PASSWORD);
    private List<VM> vmList;

    public VMController() {
        final Path idRsa = Paths.get(System.getProperty("user.home"), ".ssh", "id_rsa");
        final Path knownHosts = Paths.get(System.getProperty("user.home"), ".ssh", "known_hosts");
        if (Files.exists(idRsa)) {
            try {
                sch.setKnownHosts(knownHosts.toString());
                sch.addIdentity(idRsa.toString());
            } catch (JSchException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @GetMapping("/vm")
    public List<VM> fetchVMs() {
        final VM vm = new VM();
        vm.setName("VM-1");
        return Collections.singletonList(vm);
    }

    public String getAuthenticationHeader(String username, String password) {
        String authString = username + ":" + password;
        String base64Creds = new Base64().encodeAsString(authString.getBytes());
        return "Basic " + base64Creds;
    }

    @PostMapping("/connect/vc")
    public String connectVC(@RequestParam String admin,
                            @RequestParam("password") String password)
            throws IOException, NoSuchAlgorithmException, KeyManagementException {
//        final OkHttp3ClientHttpRequestFactory http3ClientFactory =
//                new OkHttp3ClientHttpRequestFactory();

        SSLContext sslContext = SSLContext.getInstance("SSL");
        sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
        OkHttpClient.Builder newBuilder = new OkHttpClient.Builder();
        newBuilder.sslSocketFactory(sslContext.getSocketFactory(), (X509TrustManager) trustAllCerts[0]);
        newBuilder.hostnameVerifier((hostname, session) -> true);
        okHttpClient = newBuilder.build();

        final FormBody body = new FormBody.Builder().build();
        authenticationHeader = getAuthenticationHeader(admin, password);
        System.out.println(String.format("#vc-authenticationHeader# %s", authenticationHeader));
        final Request authorization = new Request.Builder()
                .addHeader("Authorization", authenticationHeader)
                .url("https://" + VC + "/api/session")
                .post(body)
                .build();
        final Call call = okHttpClient.newCall(authorization);
        final Response result = call.execute();

        final String content = result.body().string();
        System.out.println(String.format("#vc-conn# %s", content));
        token = objectMapper.readValue(content, VCToken.class);
        return result + token.toString();
    }

    private Request.Builder buildRequest() {
        return new Request.Builder()
                .addHeader("Authorization", authenticationHeader)
                .addHeader("vmware-api-session-id", token.getToken());
    }

    private Request.Builder buildVCRequest(String api) {
        return buildRequest().url("https://" + VC + api);
    }

    private Request.Builder buildNSXRequest(String api) {
        return new Request.Builder()
                .addHeader("Authorization", nsxAuthenticationHeader)
                .url("https://" + NSX + api);
    }

    @GetMapping("/test/perf/result")
    public String testPerfResult() throws IOException {
        StringBuilder resultLines = new StringBuilder();

        vmList.forEach(vm -> {
            resultLines.append(readFile(vm, CheckType.PERF));
            resultLines.append('\n');
            resultLines.append('\n');
            resultLines.append("****************************************************************************************");
            resultLines.append('\n');
            resultLines.append('\n');
        });

        Request request = buildNSXRequest("/api/v1/infra/sha/runbook-invocations/perf/report")
                .get()
                .build();
        Call hostCall = okHttpClient.newCall(request);
        Response hostResult = hostCall.execute();
        resultLines.append("runbook exec result: \n" + hostResult.body().string());

        request = buildNSXRequest("/api/v1/infra/sha/runbook-invocations/perf")
                .delete()
                .build();
        hostCall = okHttpClient.newCall(request);
        hostCall.execute();

        return resultLines.toString();
    }

    @GetMapping("/test/l2/result")
    public String testL2Result() {
        StringBuilder resultLines = new StringBuilder();
        vmList.stream().filter(VMController::isPingStartPoint).forEach(vm -> {
            resultLines.append(readFile(vm, CheckType.PING));
        });

        return resultLines.toString();
    }

    private StringBuilder readFile(VM vm, CheckType type) {
        StringBuilder resultLines = new StringBuilder();
        try {
            final Remote sshd = new Remote(vm.getIp(), CANARY_USER, CANARY_PASSWORD);
            Session session = sch.getSession(sshd.getUser(), sshd.getHost(), sshd.getPort());
            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
            session.setUserInfo(sshd);
            session.connect();

            ChannelSftp channel = (ChannelSftp) session.openChannel("sftp");
            channel.connect();

            List<String> result;
            switch (type) {
                case PING:
                    result = Arrays.asList(CONN_RESULT_OUTPUT, CONN_RESULT_OUTPUT_MORE);
                    break;
                default:
                    result = Arrays.asList(PERF_RESULT_OUTPUT);
            }

            for (String path : result) {
                try (InputStream stream = channel.get(path);
                     InputStreamReader streamReader = new InputStreamReader(stream);
                     BufferedReader br = new BufferedReader(streamReader)) {
                    final StringJoiner joiner = new StringJoiner("\n");
                    br.lines().forEach(joiner::add);
                    resultLines.append(joiner.toString());
                }
            }

            channel.disconnect();
            session.disconnect();
        } catch (JSchException | IOException | SftpException e) {
            System.out.printf("#test-read-result-exception# %s\n", e);
            resultLines.append(String.format("#test-read-result-exception# %s", e));
            throw new RuntimeException(e);
        } finally {
            return resultLines;
        }
    }

    @GetMapping("/test/perf")
    public String testPerf() throws IOException {
        // get canary vm by name
        listVM();

        // run script in each vm
        StringBuilder sb = new StringBuilder();
        Collections.reverse(vmList);
        vmList.forEach(vm -> {
            new Thread(() -> {
                final String result = perfVM(vm);
                sb.append(result).append("\n");
            }).start();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        RequestBody data = RequestBody.create(
                MediaType.parse("application/json"), PERF_DATA);
        final Request request = buildNSXRequest("/api/v1/infra/sha/runbook-invocations/perf")
                .post(data)
                .build();
        final Call hostCall = okHttpClient.newCall(request);
        final Response hostResult = hostCall.execute();
        sb.append("runbook exec code: " + hostResult.code());

        return sb.toString();
    }

    private static final String PERF_DATA = "{\n" +
            "    \"runbook_path\": \"/infra/sha/pre-defined-runbooks/00000000-0000-0000-506e-696350657266\",\n" +
            "    \"target_node\": \"f16a03fc-2743-4e0c-aa91-f8ef430a661c\",\n" +
            "    \"arguments\": [\n" +
            "        {\n" +
            "            \"key\": \"lsp\",\n" +
            "            \"value\": \"731c9d58-1962-4b5b-809d-7fa0fbc37481\"\n" +
            "        }\n" +
            "    ]\n" +
            "}";

    @GetMapping("/test/l2/{kind}")
    public String testL2(@PathVariable(value = "kind") String kind) throws IOException {
        // get canary vm by name
        listVM();

        // run script in each vm
        StringBuilder sb = new StringBuilder();
        vmList.forEach(vm -> {
            if (isPingStartPoint(vm)) {
                String result = null;
                switch (kind) {
                    case "mtu":
                        result = mtuPingVM(vm);
                        break;
                    default:
                        result = pingVM(vm);
                }
                sb.append(result).append("\n");
            }
        });

        return sb.toString();
    }

    private static boolean isPingStartPoint(VM vm) {
        return vm.getName().equals(PING_START_POINT);
    }

    @GetMapping("/test/vm")
    public String listVM() throws IOException {
        final Request request = buildVCRequest("/api/vcenter/vm")
                .get()
                .build();
        try (Response response = okHttpClient.newCall(request).execute()) {
            final ResponseBody body = response.body();
            vmList = objectMapper.readValue(body.string(), new TypeReference<List<VM>>() {
            });
            vmList = vmList.stream()
                    .filter(vm -> vm.getName().startsWith(CANARY_PHOTON_PREFIX))
                    .collect(Collectors.toList());
        }

        System.out.println(String.format("#test-list-vm# %s", vmList));

        final Set<VM> vmSet = vmList.stream()
                .filter(vm -> vm.getPower_state().equals("POWERED_OFF"))
                .collect(Collectors.toSet());
        vmSet.forEach(this::powerOnVM);
        System.out.println(String.format("#power-on# %s", vmSet));
        // sleep here in case taking time to boot VMs
        vmList.forEach(this::retrieveVMIf);
        System.out.println(String.format("#vm-ip# %s", vmList));

        ObjectMapper objectMapper = new ObjectMapper();
        String vmArray = objectMapper.writeValueAsString(vmList);
        return vmArray;
    }

    public String runPerfBook() throws IOException {
        final Request request = buildVCRequest("/api/vcenter/vm")
                .get()
                .build();
        try (Response response = okHttpClient.newCall(request).execute()) {
            final ResponseBody body = response.body();
            vmList = objectMapper.readValue(body.string(), new TypeReference<List<VM>>() {
            });
            vmList = vmList.stream()
                    .filter(vm -> vm.getName().startsWith(CANARY_PHOTON_PREFIX))
                    .collect(Collectors.toList());
        }

        System.out.println(String.format("#test-list-vm# %s", vmList));

        final Set<VM> vmSet = vmList.stream()
                .filter(vm -> vm.getPower_state().equals("POWERED_OFF"))
                .collect(Collectors.toSet());
        vmSet.forEach(this::powerOnVM);
        System.out.println(String.format("#power-on# %s", vmSet));
        // sleep here in case taking time to boot VMs
        vmList.forEach(this::retrieveVMIf);
        System.out.println(String.format("#vm-ip# %s", vmList));

        ObjectMapper objectMapper = new ObjectMapper();
        String vmArray = objectMapper.writeValueAsString(vmList);
        return vmArray;
    }

    public static enum CheckType {
        PING, PING1, PING2, PERF
    }

    private String pingVM(VM vm) {
        return execCheck(vm, CheckType.PING);
    }

    private String mockPingVM(VM vm) {
        return execCheck(vm, CheckType.PING1);
    }

    private String mtuPingVM(VM vm) {
        return execCheck(vm, CheckType.PING2);
    }

    private String perfVM(VM vm) {
        return execCheck(vm, CheckType.PERF);
    }

    @NotNull
    private String execCheck(VM vm, CheckType type) {
        StringBuilder resultLines = new StringBuilder();
        try {
            final Remote sshd = new Remote(vm.getIp(), CANARY_USER, CANARY_PASSWORD);
            Session session = sch.getSession(sshd.getUser(), sshd.getHost(), sshd.getPort());
            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
            session.setUserInfo(sshd);
            session.connect();

            Channel channel = session.openChannel("exec");
            switch (type) {
                case PING:
                    ((ChannelExec) channel).setCommand(CONNECTION_CHECK_SH
                            + " " + "192.168.100.12"
                            + " " + "192.168.100.18");
                    break;
                case PING1:
                    ((ChannelExec) channel).setCommand(CONNECTION_CHECK_SH
                            + " " + "192.168.100.112"
                            + " " + "192.168.100.18");
                    break;
                case PING2:
                    ((ChannelExec) channel).setCommand(MTU_CHECK_SH
                            + " " + "192.168.100.12"
                            + " " + "192.168.100.18"
                            + " " + "1518");
                    break;
                default:
                    ((ChannelExec) channel).setCommand(PERF_CHECK_SH);
            }

            channel.setInputStream(null);
            ((ChannelExec) channel).setErrStream(System.err);
            InputStream in = channel.getInputStream();
            channel.connect();

            byte[] tmp = new byte[1024];
            while (true) {
                while (in.available() > 0) {
                    int i = in.read(tmp, 0, 1024);
                    if (i < 0) break;
                    System.out.print(new String(tmp, 0, i));
                }
                if (channel.isClosed()) {
                    System.out.println("exit-status: " + channel.getExitStatus());
                    resultLines.append(String.format("exit-status: %s", channel.getExitStatus()));
                    break;
                }
                Thread.sleep(1000);
            }

            channel.disconnect();
            session.disconnect();
        } catch (JSchException | InterruptedException | IOException e) {
            System.out.printf("#test-exec-exception# %s", e);
            resultLines.append(String.format("#test-exec-exception# %s", e));
            throw new RuntimeException(e);
        }
        return resultLines.toString();
    }

    private VM powerOnVM(VM vm) {
        final FormBody body = new FormBody.Builder().build();
        final Request request = buildVCRequest(
                String.format("/api/vcenter/vm/%s/power?action=start", vm.getVm()))
                .post(body)
                .build();
        try (Response response = okHttpClient.newCall(request).execute()) {
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            return vm;
        }
    }

    private VM retrieveVMIf(VM vm) {
        //https://{{api_host}}/api/vcenter/vm/vm-25/guest/networking/interfaces
        final Request request = buildVCRequest(
                String.format("/api/vcenter/vm/%s/guest/networking/interfaces", vm.getVm()))
                .get()
                .build();
        try (Response response = okHttpClient.newCall(request).execute()) {
            final JsonNode jsonNode = objectMapper.readTree(response.body().string());
            if (jsonNode.isArray()) {
                JsonNode node = jsonNode.get(0);
                JsonNode ipList = node.path("ip").path("ip_addresses");
                if (ipList.isArray()) {
                    StreamSupport.stream(
                                    Spliterators.spliteratorUnknownSize(
                                            ipList.elements(),
                                            Spliterator.ORDERED), false)
                            .filter(cnode -> cnode.path("state").asText().equals("PREFERRED"))
                            .forEach(fnode -> {
                                vm.setIp(fnode.get("ip_address").asText());
                            });
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            return vm;
        }
    }

    private String perfHost() {
        StringBuilder resultLines = new StringBuilder();
        try {
            final Remote sshd = new Remote("10.185.100.18", CANARY_USER, "T7#FbsC+aJE#");
            Session session = sch.getSession(sshd.getUser(), sshd.getHost(), sshd.getPort());
            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            config.put("PreferredAuthentications", "password");
            session.setConfig(config);
            session.setUserInfo(sshd);
//            session.setPassword("T7#FbsC+aJE#");
            session.connect();

            Channel channel = session.openChannel("exec");
            String command = "vmkperf listevents";
            ((ChannelExec) channel).setCommand(command);
            channel.setInputStream(null);
            ((ChannelExec) channel).setErrStream(System.err);
            InputStream in = channel.getInputStream();
            channel.connect();

            byte[] tmp = new byte[1024];
            while (true) {
                while (in.available() > 0) {
                    int i = in.read(tmp, 0, 1024);
                    if (i < 0) break;
                    final String line = new String(tmp, 0, i);
                    resultLines.append(line);
                    System.out.print(line);
                }
                if (channel.isClosed()) {
                    System.out.println("exit-status: " + channel.getExitStatus());
                    resultLines.append(String.format("exit-status: %s", channel.getExitStatus()));
                    break;
                }
                Thread.sleep(1000);
            }

            channel.disconnect();
            session.disconnect();
        } catch (JSchException | InterruptedException | IOException e) {
            System.out.printf("#test-host-exception# %s", e);
            resultLines.append(String.format("#test-host-exception# %s", e));
            throw new RuntimeException(e);
        }
        return resultLines.toString();
    }

    @PostMapping("/test/{case}")
    public String testReadiness(@PathVariable("case") String readinessCase) throws IOException {
        // host
        final Request hostInfo = new Request.Builder()
                .addHeader("Authorization", " Basic YWRtaW5pc3RyYXRvckB2c3BoZXJlLmxvY2FsOlpQSkNwdFVqM3gtc1h6OF8=")
                .addHeader("vmware-api-session-id", token.getToken())
                .url("https://" + VC + "/api/vcenter/host")
                .get()
                .build();
        final Call hostCall = okHttpClient.newCall(hostInfo);
        final Response hostResult = hostCall.execute();

        // tz
        final Request tzInfo = new Request.Builder()
                .addHeader("Authorization", " Basic YWRtaW46VDcjRmJzQythSkUj=")
                .url("https://" + NSX + "/api/v1/transport-zones")
                .get()
                .build();
        Call tzCall = okHttpClient.newCall(tzInfo);
        final Response tzResult = tzCall.execute();

        // tn
        final Request tnInfo = new Request.Builder()
                .addHeader("Authorization", " Basic YWRtaW46VDcjRmJzQythSkUj=")
                .url("https://" + NSX + "/api/v1/transport-nodes")
                .get()
                .build();

        // deploy canary vm

        // power on canary vm

        // run ping case

        // power off canary vm

        // remove canary vm


        return hostResult.body().string();
    }

    @PostMapping("/test/ping")
    public String testPing() throws JSchException, IOException {
        StringBuilder resultLines = new StringBuilder();

        final Remote sshd = new Remote("10.185.103.89", CANARY_USER, CANARY_PASSWORD);
        if (Files.exists(Paths.get(sshd.getIdentity()))) {
//            jsch.addIdentity(sshd.getIdentity(), sshd.getPassphrase());
            sch.addIdentity(sshd.getIdentity());
        }
        sch.setKnownHosts("~/.ssh/known_hosts");
        sch.addIdentity(sshd.getIdentity());
        Session session = sch.getSession(sshd.getUser(), sshd.getHost(), sshd.getPort());
        session.setUserInfo(sshd);
        session.connect();

        Channel channel = session.openChannel("exec");
        String command = "ping -c3 www.google.com";
        ((ChannelExec) channel).setCommand(command);
        channel.setInputStream(null);
        ((ChannelExec) channel).setErrStream(System.err);
        InputStream in = channel.getInputStream();
        channel.connect();

        byte[] tmp = new byte[1024];
        while (true) {
            while (in.available() > 0) {
                int i = in.read(tmp, 0, 1024);
                if (i < 0) break;
                final String s = new String(tmp, 0, i);
                System.out.print(s);
                resultLines.append(s);
            }
            if (channel.isClosed()) {
                if (in.available() > 0) continue;
                final String err = "exit-status: " + channel.getExitStatus();
                System.out.println(err);
                resultLines.append(err);
                break;
            }
            try {
                Thread.sleep(1000);
            } catch (Exception ee) {
            }
        }
        channel.disconnect();
        session.disconnect();


        return resultLines.toString();

    }

    public class Remote implements UserInfo {
        private String host;
        private final int port = 22;
        private String user;
        private String password;
        //        private final String identity = "~/.ssh/id_rsa";
        private final String identity = "~/.ssh/id_ed25519";
        private String passphrase;

        public Remote(String host, String user, String password) {
            this.host = host;
            this.user = user;
            this.password = password;
        }

        public String getIdentity() {
            return identity;
        }

        public String getHost() {
            return host;
        }

        public int getPort() {
            return port;
        }

        public String getUser() {
            return user;
        }

        @Override
        public String getPassphrase() {
            return null;
        }

        @Override
        public String getPassword() {
            return this.password;
        }

        @Override
        public boolean promptPassword(String s) {
            return true;
        }

        @Override
        public boolean promptPassphrase(String s) {
            return false;
        }

        @Override
        public boolean promptYesNo(String s) {
            return false;
        }

        @Override
        public void showMessage(String s) {

        }
    }

    static class VCToken {
        String token;

        public VCToken(String token) {
            this.token = token;
        }

        @Override
        public String toString() {
            return "VCToken{" +
                    "token='" + token + '\'' +
                    '}';
        }

        public String getToken() {
            return token;
        }
    }
}
